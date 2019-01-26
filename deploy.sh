#!/usr/bin/env bash

# FIELD
PROFILE_NAME="production"
PROJECT_NAME="yellowcard-api"

NEW_JAR_FULL_PATH="/home/ec2-user/web/${PROJECT_NAME}-0.0.1.jar"
JAR_FULL_PATH="/home/ec2-user/web/${PROJECT_NAME}.jar"
PID_FULL_PATH="/home/ec2-user/web/${PROJECT_NAME}.pid"

JAVA_OPTS="-server --spring.profiles.active=${PROFILE_NAME}"

# COMMAND (OPTION)
case $1 in
    start)
        echo "Starting $PROJECT_NAME ..."
        if [ -f ${PID_FULL_PATH} ]; then
            echo "$PROJECT_NAME is already running ..."
        else
            if [ -f ${NEW_JAR_FULL_PATH} ]; then
                chmod +x ${NEW_JAR_FULL_PATH}
                rm ${JAR_FULL_PATH}
                mv ${NEW_JAR_FULL_PATH} ${JAR_FULL_PATH}
            fi

#           java -jar ${JAR_FULL_PATH} ${JAVA_OPTS}
            nohup java -jar ${JAR_FULL_PATH} ${JAVA_OPTS} /tmp 2>> /dev/null >> /dev/null & echo $! > ${PID_FULL_PATH}
            echo "$PROJECT_NAME started ..."
        fi
    ;;
    stop)
        if [ -f ${PID_FULL_PATH} ]; then
            PID=$(cat ${PID_FULL_PATH});
            echo "$PROJECT_NAME stopping ..."
            kill ${PID};
            echo "$PROJECT_NAME stopped ..."
            rm ${PID_FULL_PATH}
        else
            echo "$PROJECT_NAME is not running ..."
        fi
    ;;
esac