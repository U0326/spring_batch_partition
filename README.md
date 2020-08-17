# spring_batch_partition
## 説明
Spring BatchのPartitionを利用した、サンプルを実装したプロジェクトになります。  
本プロジェクトは、[こちら](https://github.com/U0326/spring_batch_commandline_runner_template)を元にしています。


## 要求事項
* Java 8以上
* Maven 3.6.3以上
* Docker

## 起動方法
プロジェクトルートで以下コマンドを発行してください。
```
# libフォルダにライブラリを出力します。
mvn dependency:copy-dependencies -DoutputDirectory=lib
# ビルドします。
mvn clean package
# DBを用意します。
docker run --name postgres --rm -v `pwd`/initdb:/docker-entrypoint-initdb.d -e POSTGRES_PASSWORD=password -p 5432:5432 postgres -c log_destination=stderr -c log_statement=all -c log_connections=on -c log_disconnections=on
# 起動します。
java -cp 'target/*:lib/*' org.springframework.batch.core.launch.support.CommandLineJobRunner -next job-setting.xml job01
```
