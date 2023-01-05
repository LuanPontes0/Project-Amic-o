Instruções para fazer nosso sistema funcionar
1.	Tenha o banco de dados MySql instalado em sua máquina.
2.	Em seu banco de dados, execute a seguinte linha de comandos para ser criado a database: 
3.	create database petshop
use petshop
create table usuario
(
id integer auto_increment,
nome varchar(50),
email varchar(50),
senha varchar(50),
primary key(id)
)						
4.	Após abrir o nosso projeto em seu editor de texto, siga o seguinte caminho de diretórios:
/apiPetshop/src/main/resources/application.properties
5.	Após encontrar o arquivo, você precisara efetuar apenas duas pequenas alterações, ou três se tiver o nome de usuário no bd diferente de “root”: no campo (spring.datasource.username=) você irá inserir o seu nome de usuário no bd, no campo seguinte (spring.datasource.password=) você irá colocar a senha que cadastrou no seu usuário no bd, e por último, para que a aplicação fique acessível a dispositivos em sua rede, (server.adress=) o ip de sua máquina.
6.	Após ter configurado estes 3 itens e ter o bd my sql devidamente instalado, o sistema já está pronto para funcionar, inicie ele, e após no prompt ele informar que a aplicação foi iniciada, digite em seu navegador o seguinte caminho http://localhost:8090/ ou o seu ip por ex: http://192.168.100.174:8090/
7.	Concluído, agora é só efetuar um cadastro e disfrutar de todas as funcionalidades que nosso sistema pode oferecer! Mas atenção, há dois tipos de cadastros em nosso sistema, admin e usuário, cada um com suas respectivas funções!
