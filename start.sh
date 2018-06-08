a=$(ps -ef | grep mq_platfrom-1.0.jar | grep -v grep |grep -v bash | awk '{ print $2 }')
wait
if [ -n "$a" ];then
        echo "platfrom is running";
else
        nohup java -jar ./mq_platfrom-1.0.jar > /dev/null 2>&1 &
wait
fi
