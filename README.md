# TuaObra

TuaObra é uma plataforma desenvolvida para conectar pedreiros, clientes e casas de construção no contexto da construção civil. 
## Índice

- [Introdução](#introdução)
- [Funcionalidades](#funcionalidades)
- [Como Executar](#como-executar)

## Introdução

O sistema TuaObra tem como objetivo conectar pedreiros, clientes e casas de construção de Garanhuns e região. A aplicação permite aos clientes encontrar pedreiros qualificados, enquanto as casas de construção podem receber orçamentos de forma mais eficiente. A solução foca na cultura local para oferecer uma experiência mais próxima do cotidiano dos usuários.

## Funcionalidades

### Para Pedreiros
- **Criar Perfil**: Cadastro com foto, nome, contato de WhatsApp, endereço, descrição do que faz e suas especialidades prestadas.
- **Ver Demandas**: Visualização das demandas cadastradas pelos clientes.

### Para Clientes
- **Criar Perfil**: Cadastro com foto, nome, endereço e contato.
- **Ver Serviços Feitos**: Acesso a fotos e vídeos de serviços realizados por pedreiros.
- **Cadastrar Demanda**: Registro de demandas com detalhes sobre o trabalho necessário.
- **Enviar Orçamento**: Envio de listas de orçamento para casas de construção.
- **Avaliar Pedreiro**: Avaliação dos serviços prestados pelos pedreiros.
- **Avaliar Casa de Construção**: Avaliação das casas de construção.

### Para Casas de Construção
- **Criar Perfil**: Cadastro com foto, nome, descrição, endereço, contato, horários, formas de pagamento e frete.
- **Receber Lista de Orçamento**: Recebimento de listas de orçamento dos clientes.
## Como Executar

1. **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/tuaobra.git
    ```

2. **Configure as variáveis de ambiente:**
    - Crie um arquivo `.env` na raiz do projeto e adicione as variáveis necessárias (Firebase, banco de dados, etc.).

3. **Execute o backend:**
    ```bash
    cd backend
    ./mvnw spring-boot:run
    ```

4. **Legendas para os comandos dos Commits**
- **[feat] - para novos recursos**:
- **[fix] - para correções de bugs**
- **[docs] - mudanças na documentação**
- **[style] - mudanças de formatação, sem impacto no código**
- **[refactor] - refatoração de código, sem adição de novos recursos ou correção de bugs**
- **[test] - adição ou alteração de testes**
- **[chore] - tarefas menores, como mudanças de configurações**
