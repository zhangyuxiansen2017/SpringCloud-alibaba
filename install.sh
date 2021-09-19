#!/bin/bash
if [[ -z $(which mvn) ]] ;
then
    echo 'mvn does not exist'
    return
fi

if [ -n "$*"  ];
then
  for file in "$@" ;
  do
    if [ -f "$file/Dockerfile" ] ;
    then
        # compile
        mvn -Dmaven.test.skip=true -gs mvn-settings.xml clean package -pl "$file" -am
        echo "$file" && cd "$file" || exit
        # build images and deployment
        docker build -f Dockerfile -t "$file" . && kubectl apply -f deploy/*.yaml
        cd ../
    fi
  done
else
  # compile
  mvn -Dmaven.test.skip=true -gs mvn-settings.xml clean package
  for file in * ;
  do
    if [ -f "$file/Dockerfile" ] ;
    then
      echo "$file" && cd "$file" || exit
      # build images and deployment
      docker build -f Dockerfile -t "$file" . && kubectl apply -f deploy/*.yaml
      cd ../
    fi
  done
fi

echo "complete ～ Oh yeah!!!"
