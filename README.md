Springboot

**_Para criar como serviço local utilizando seu jar:_**

**Criando o jar**: mvn package

sudo ln -s /Users/{username}/Documents/Spring_Framework/SpringBoot/springboot_essentials/code/SpringBoot/target/devxablau.jar /etc/init.d/devxablau

**_Para executar automaticamente quando reiniciar o sistema operacional:_**
sudo update.rc.d devxablau defaults

**_Para iniciar o serviço manualmente:_**
sudo service devxablau start

**_Verificando os status do serviço:_**
sudo service devxablau status