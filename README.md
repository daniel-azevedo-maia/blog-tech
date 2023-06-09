# blog-tech

BlogTech é uma aplicação web baseada em Java Spring Boot que fornece um sistema básico de blog com autenticação de usuário. A aplicação permite que os usuários se registrem, façam login e criem novas postagens de blog.

Tecnologias Utilizadas:
-Java
-Spring Boot
-Spring Security
-Thymeleaf (Template Engine)
-PostgreSQL (Banco de dados)

Funcionalidades:

-Cadastro de Usuário: Permite que os usuários se registrem fornecendo informações básicas como nome, sobrenome, e-mail, login e senha.
-Criação de Postagem: Após o login, os usuários podem criar novas postagens no blog. Eles podem adicionar um título, subtítulo, categoria e o texto principal para a postagem.

Estrutura do Código:
A estrutura do código é baseada no padrão MVC (Model-View-Controller) e é composta por várias partes principais:
Modelos: As classes Post, Usuario e Role representam as entidades principais no sistema e são mapeadas para tabelas correspondentes no banco de dados.
Repositórios: PostRepository e UsuarioRepository são interfaces que estendem JpaRepository para fornecer operações CRUD básicas para as entidades Post e Usuario.
Serviços: PostService e UsuarioService contêm a lógica de negócios principal e interagem com os repositórios para persistir dados.
Controladores: PostController e UsuarioController gerenciam a interação do usuário, tratam solicitações HTTP e retornam respostas. Eles utilizam os serviços para realizar ações como criar uma nova postagem ou registrar um novo usuário.

Configuração e Uso
-Para executar a aplicação, você precisa ter um ambiente de desenvolvimento Java configurado com acesso a um banco de dados PostgreSQL.
-Depois de clonar o repositório, você pode importar o projeto em seu IDE favorito e executá-lo a partir daí. A aplicação está configurada para iniciar na porta 8080, então você pode acessar a interface do usuário indo para http://localhost:8080 em seu navegador.
-Para adicionar novos usuários ou postagens, você pode usar os endpoints /usuario/novo e /post/novo, respectivamente.
