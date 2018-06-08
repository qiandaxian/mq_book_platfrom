a=$(ps -ef | grep mq_platfrom-1.0.jar | grep -v grep |grep -v bash | awk '{ print $2 }')
wait
if [ -n "$a" ];then
        echo "stopping platfrom($a)"
        kill -9 $a
        sleep 2
else
        echo "middleWare is not running";
wait
fi