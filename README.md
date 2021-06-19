<h4 align="left">
 Catalogo de produtos com Spring Boot, PostgreSQL, front-end em ReactJS em construção
</h4>


---

### Autor

<a href="https://www.linkedin.com/in/marcelomachado1987/">
 <img style="border-radius: 50%;" src="https://media-exp1.licdn.com/dms/image/C4E03AQEwV54JjLc-9g/profile-displayphoto-shrink_800_800/0/1621682542460?e=1626912000&v=beta&t=Ctis1Z8wFBsNtnuMhTXGp7cXWA12JyY5t9KF9rfQf58" width="100px;" alt=""/>
 <br />
<b>Marcelo Machado</b></a>
 <br />

---

### Features

- [x] CRUD Categorias com verbos HTTP
- [x] CRUD Produtos com verbos HTTP
- [x] CRUD Clientes com verbos HTTP
- [x] CRUD Permissões com verbos HTTP
- [x] Testes automatizados
- [ ] Autenticação com Token JWT+OAuth2
- [ ] Front-end em ReactJS

---

### Tecnologias (Back-end)

As seguintes ferramentas foram usadas na construção do projeto:

- [Spring Boot](https://spring.io/projects)
- [Java 11](https://docs.oracle.com/en/java/javase/11/)
- [Hibernate + JPA](https://hibernate.org/)
- [SQL]
- [JWT](https://jwt.io/)
- [OAuth 2.0](https://oauth.net/2/)

---

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

* IDE para desenvolvimento JAVA como Inteliji(a mesma que usei) ou Spring Tool Suite 4
* Gerenciador de dependencias maven
* para testar os verbos HTTP utilize o Insominia ou Postman

---

###Rodando o Back End (servidor)

```bash
# Clone este repositório
$ git clone <>

# Importe o projeto na sua IDE

# Aguarde a sincronização das dependencias do Maven

# Execute a aplicação (está no profile test por se tratar de um projeto desenvolvido com intuito de estudo)

#caso queira alterar o seed do banco de dados altere os dados no arquivo resoruces/data.sql

# O servidor inciará na porta:8080 - abra o Insominia/Postman e execute os verbos HTML na url <http://localhost:8080/clients>

```

---

[Exemplos de saidas/requisições com verbos HTTP no Backend](https://github.com/MarceloMachadoxD/CatalogoDeProdutos/blob/a102c64260a10f5051fb2b523789f9b9ae8ce13b/backend/BACKEND.MD)