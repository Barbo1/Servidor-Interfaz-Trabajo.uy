home = $(shell echo $(HOME))
compilar_int:
	@[ -d "./paquete" ] || { mkdir ./paquete; }
	cd ./estacionTrabajo && mvn clean install
	cd ./estacionTrabajo/target && mv estacionTrabajo-0.0.1-SNAPSHOT-jar-with-dependencies.jar estacionTrabajo.jar && mv estacionTrabajo.jar ../../paquete

compilar_ser:
	cd ./servidorWeb && mvn clean install
	cd ./servidorWeb/target && mv servidorWeb-0.0.1-SNAPSHOT.war servidorWeb.war && mv servidorWeb.war ../../paquete

compilar:
	make compilar_int
	make compilar_ser

interfaz:
	cd ./paquete && java -jar estacionTrabajo.jar

rebuild:
	make clean
	make

