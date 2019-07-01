部署步骤：
1，eclipse导出war包

2. 本地war包上传至服务器
   scp -P 22 cd-qlqq.war root@120.55.114.78:/home  
   T6dh%$%$ss1
   
     或者手动放到/home目录

3. 备份原先配置(如果第一次部署，跳过此步骤)
  ssh root@121.43.101.148 -p 57652

  cd /home/wwwroot/ride/tomcat_qlqq_biz/webapps
  cp ./cd-qlqq/WEB-INF/classes/application.properties .
  cp ./cd-qlqq/WEB-INF/classes/config.properties .
  rm -rf cd-qlqq*
  mv /home/cd-qlqq.war .
  
4. 已备份配置文件放回原处,重启tomcat
  mv -f application.properties ./cd-qlqq/WEB-INF/classes/
  mv -f config.properties ./cd-qlqq/WEB-INF/classes/
  
  ../bin/shutdown.sh
  ../bin/startup.sh
  
6. 验证程序
  http://120.55.114.78:5702/cd-qlqq/api


城市网-----------------------
3. 备份原先配置(如果第一次部署，跳过此步骤)
  ssh root@121.43.101.148 -p 57652

  cd /home/wwwroot/forum/tomcat_activity_biz/webapps
  cp ./cd-qlqq/WEB-INF/classes/application.properties .
  cp ./cd-qlqq/WEB-INF/classes/config.properties .
  rm -rf cd-qlqq*
  mv /home/cd-qlqq.war .
  
4. 已备份配置文件放回原处,重启tomcat
  mv -f application.properties ./cd-qlqq/WEB-INF/classes/
  mv -f config.properties ./cd-qlqq/WEB-INF/classes/
  
  ../bin/shutdown.sh
  ../bin/startup.sh
  
  6. 验证程序
  http://121.43.101.148:5409/cd-qlqq/api
  
  