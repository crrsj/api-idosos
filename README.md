🏥 Sistema de Gestão de Pacientes - Backend
📋 Sobre o Projeto
API REST desenvolvida em Spring Boot para gerenciamento completo de pacientes, medicamentos e endereços, com foco em saúde e acompanhamento médico.

🚀 Tecnologias Utilizadas
Backend
Java 17+ - Linguagem principal

Spring Boot 3.x - Framework principal

Spring Data JPA - Persistência de dados

Spring Web - APIs REST

Spring Validation - Validação de dados

Spring Doc OpenAPI - Documentação da API

Banco de Dados
MySQL/PostgreSQL - Banco de dados relacional

H2 Database - Banco em memória (desenvolvimento)

Flyway/Liquibase - Versionamento de banco (opcional)

Ferramentas
Maven/Gradle - Gerenciamento de dependências

Lombok - Redução de boilerplate code

ModelMapper - Conversão DTO/Entity

JUnit 5 - Testes unitários

Mockito - Testes com mocks

📁 Estrutura do Projeto
text
src/main/java/com/gestaopacientes/
├── config/                    # Configurações
│   ├── CorsConfig.java       # Configuração CORS
│   ├── JacksonConfig.java    # Configuração JSON
│   └── SwaggerConfig.java    # Documentação API
│
├── controller/               # Controladores REST
│   ├── PacienteController.java
│   ├── MedicamentoController.java
│   └── EnderecoController.java
│
├── service/                  # Lógica de negócio
│   ├── PacienteService.java
│   ├── MedicamentoService.java
│   └── EnderecoService.java
│
├── repository/               # Repositórios JPA
│   ├── PacienteRepository.java
│   ├── MedicamentoRepository.java
│   └── EnderecoRepository.java
│
├── model/                    # Entidades JPA
│   ├── Paciente.java
│   ├── Medicamento.java
│   ├── Endereco.java
│   └── enums/
│       └── Via.java
│
├── dto/                      # Data Transfer Objects
│   ├── PacienteDTO.java
│   ├── MedicamentoDTO.java
│   ├── EnderecoDTO.java
│   └── response/
│       └── ApiResponse.java
│
├── exception/               # Tratamento de exceções
│   ├── GlobalExceptionHandler.java
│   ├── PacienteNaoEncontradoException.java
│   ├── MedicamentoNaoEncontradoException.java
│   └── EnderecoNaoEncontradoException.java
│
└── GestaoPacientesApplication.java  # Classe principal
🏗️ Modelo de Dados
Paciente
java
@Entity
@Table(name = "tb_pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String dataCadastro;
    
    @NotBlank
    private String nome;
    
    @NotBlank
    private String telefone;
    
    @NotNull
    private Integer idade;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"paciente"})
    private List<Medicamento> medicamentos;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"paciente"})
    private List<Endereco> enderecos;
}
Medicamento
java
@Entity
@Table(name = "tb_medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeMedicamento;
    
    @Enumerated(EnumType.STRING)
    private Via via;
    
    private String enfermidade;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties({"medicamentos", "enderecos"})
    private Paciente paciente;
}
Endereco
java
@Entity
@Table(name = "tb_enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties({"medicamentos", "enderecos"})
    private Paciente paciente;
    
    private String complemento;
}
Enum Via
java
public enum Via {
    ORAL,
    INTRAVENOSA,
    INTRAMUSCULAR,
    SUBCUTANEA,
    SUBLINGUAL,
    TOPICA,
    INALATORIA,
    TRANSDERMICA,
    OCULAR,
    OTOLOGICA,
    NASAL
}
🔧 Configuração
application.properties
properties
# Servidor
server.port=8080
server.servlet.context-path=/api

# Banco de Dados MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/gestao_pacientes
spring.datasource.username=root
spring.datasource.password=senha123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# H2 (Desenvolvimento)
#spring.datasource.url=jdbc:h2:mem:gestaodb
#spring.datasource.driverClassName=org.h2.Driver
#spring.datasource.username=sa
#spring.datasource.password=
#spring.h2.console.enabled=true

# Logging
logging.level.com.gestaopacientes=DEBUG
logging.level.org.springframework.web=INFO
pom.xml (Maven)
xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.5</version>
    </parent>
    
    <groupId>com.gestaopacientes</groupId>
    <artifactId>gestao-pacientes-api</artifactId>
    <version>1.0.0</version>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <!-- Banco de Dados -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Utilitários -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.modelmapper</groupId>
            <artifactId>modelmapper</artifactId>
            <version>3.1.1</version>
        </dependency>
        
        <!-- Documentação API -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.2.0</version>
        </dependency>
        
        <!-- Testes -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
🚀 Endpoints da API
Pacientes
text
GET    /api/paciente                    # Listar todos (paginação)
GET    /api/paciente/{id}              # Buscar por ID
GET    /api/paciente/buscarPorNome     # Buscar por nome
POST   /api/paciente                   # Cadastrar novo
PUT    /api/paciente/{id}              # Atualizar
DELETE /api/paciente/{id}              # Excluir
Medicamentos
text
GET    /api/medicamento                # Listar todos (paginação)
GET    /api/medicamento/{id}           # Buscar por ID
POST   /api/medicamento/{pacienteId}   # Cadastrar para paciente
PUT    /api/medicamento/{id}           # Atualizar
DELETE /api/medicamento/{id}           # Excluir
Endereços
text
GET    /api/endereco                   # Listar todos
GET    /api/endereco/{id}              # Buscar por ID
POST   /api/endereco/{pacienteId}      # Cadastrar para paciente
PUT    /api/endereco/{id}              # Atualizar
DELETE /api/endereco/{id}              # Excluir
📝 Exemplos de Requisições
Cadastrar Paciente
json
POST /api/paciente
{
    "nome": "João Silva",
    "telefone": "(11) 99999-9999",
    "idade": 35
}
Cadastrar Medicamento
json
POST /api/medicamento/1
{
    "nomeMedicamento": "Paracetamol",
    "via": "ORAL",
    "enfermidade": "Febre e dor",
    "descricao": "Tomar 1 comprimido a cada 8 horas"
}
Cadastrar Endereço
json
POST /api/endereco/1
{
    "rua": "Rua das Flores",
    "numero": "123",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "estado": "SP",
    "complemento": "Apartamento 101"
}

![gm1](https://github.com/user-attachments/assets/16d6c017-f8e5-4a35-8792-4bfee045746e)


![gm2](https://github.com/user-attachments/assets/d6510fd1-5048-45aa-adc1-0ffb0abc2340)
