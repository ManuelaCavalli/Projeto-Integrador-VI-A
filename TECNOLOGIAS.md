# Escolha do Ambiente e Tecnologias

Este documento detalha as escolhas técnicas feitas pela equipe para o desenvolvimento do projeto **Brick Breaker**.

* **1. Ambiente de Desenvolvimento — Android Studio
É o ambiente utilizado para desenvolver o aplicativo. Permite escrever e organizar o código, criar as telas, executar o jogo em emuladores ou dispositivos físicos, identificar erros e gerenciar as bibliotecas utilizadas no projeto.
* **2. Linguagem de Programação — Kotlin
É a linguagem utilizada para desenvolver a lógica e o funcionamento do jogo. Com ela são implementados os movimentos da bola, colisões, controle dos blocos, pontuação, níveis, regras e integração com o banco de dados.
* **3. Interface Gráfica — XML (View System)
Utilizado para construir e organizar os elementos visuais das telas do aplicativo, como menus, botões, telas de pontuação, configurações e ranking. Permite definir o posicionamento, tamanho e aparência dos componentes da interface.
* **4. Banco de Dados — Room Database e SQLite
O Room é uma biblioteca de persistência de dados do Android que facilita o trabalho com o SQLite, banco de dados utilizado para armazenar informações localmente no dispositivo. No projeto, será responsável por guardar os jogadores cadastrados, pontuações, histórico de partidas e informações do ranking, permitindo que esses dados permaneçam salvos mesmo após fechar o aplicativo.
* **5. Acesso aos Dados — DAO
O DAO (Data Access Object) é uma interface utilizada pelo Room para definir as operações realizadas no banco de dados. No projeto, será responsável por executar consultas, cadastrar jogadores, salvar resultados das partidas e recuperar as informações necessárias para exibir o histórico e o ranking.
* **6. Execução em Segundo Plano — Coroutines
As Coroutines são um recurso do Kotlin que permite executar tarefas sem bloquear a interface do aplicativo. Serão utilizadas principalmente nas operações com o banco de dados, evitando que o carregamento de informações ou o salvamento de pontuações prejudique a fluidez do jogo.
* **7. Animações — ValueAnimator
É uma ferramenta do Android utilizada para criar animações. No projeto, será utilizada para controlar animações e movimentos dos elementos visuais, contribuindo para uma experiência de jogo mais fluida.
* **8. Reprodução de Áudio — MediaPlayer
É uma classe nativa do Android utilizada para reproduzir arquivos de áudio. Será utilizada para adicionar efeitos sonoros durante as partidas, como sons de colisão, início e outros eventos do jogo.
* **9. Armazenamento de Configurações — SharedPreferences
É um mecanismo de armazenamento local utilizado para guardar pequenas informações e preferências do usuário. No projeto, será responsável por salvar configurações como cores, sons e tamanhos dos blocos, mantendo as preferências mesmo depois que o aplicativo for fechado.
10. Gerenciamento de Dependências — Gradle
É a ferramenta responsável por configurar a compilação do aplicativo e gerenciar suas dependências. Permite adicionar bibliotecas, configurar versões e organizar os recursos necessários para o funcionamento do projeto.



