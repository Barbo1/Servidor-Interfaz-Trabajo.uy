make compilar_ser
mv paquete/servidorWeb.war ../../server/apache-tomcat-10.1.12/webapps/
sh ../../server/apache-tomcat-10.1.12/bin/shutdown.sh
sh ../../server/apache-tomcat-10.1.12/bin/startup.sh
