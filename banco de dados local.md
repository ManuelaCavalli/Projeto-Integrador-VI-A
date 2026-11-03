## Por que escolhemos o Room Database?

## O SQLite é o banco de dados relacional leve, independente e padrão do sistema operacional Android, sendo a escolha ideal para este projeto pelos seguintes motivos:

- Suporte nativo e universal: Faz parte do núcleo do Android — não exige instalação de bibliotecas externas, não aumenta o tamanho do aplicativo e funciona em todos os dispositivos sem dependências adicionais.
- Controle total sobre os dados: Permite definir com precisão cada detalhe da estrutura, tipos de dados, restrições, índices e relações, sem camadas intermediárias que limitem ou escondam o funcionamento do banco.
- Compatibilidade e durabilidade: É a tecnologia de armazenamento local mais testada e estável da plataforma; código escrito para SQLite continua funcionando ao longo de versões e atualizações do Android sem risco de depreciação.
- Divisão clara de responsabilidades: O `SharedPreferences` continua sendo usado para configurações simples (cores, sons, preferências de exibição), enquanto o SQLite assume o armazenamento de dados estruturados, relacionais e em maior volume — como cadastro de jogadores, histórico de partidas e ranking.
- Evolução flexível: A estrutura pode ser ampliada com novas tabelas, campos e regras conforme o projeto cresce (conquistas, níveis salvos, estatísticas detalhadas), com total controle sobre as migrações de versão.

## Como será aplicado no projeto?

A implementação segue a arquitetura padrão do Room, organizada em camadas claras:

## 2.1 Estrutura de Dados — Entidades
Definem o que é armazenado no aparelho do usuário:
-	Tabela usuarios: Cadastro de jogadores com nome, pontuação total, maior pontuação, quantidade de partidas jogadas e nível máximo alcançado.
-	Tabela pontuacoes: Registro de cada partida jogada, ligada ao jogador por meio de um identificador (usuarioId), com pontos obtidos, nível e data.
## 2.2 Acesso aos Dados — DAO
Interface que concentra todas as operações:
-	Cadastrar ou buscar jogador;
-	Salvar resultado de partida;
-	Consultar ranking ordenado por maior pontuação (Top 10);
-	Consultar histórico de partidas de um jogador.
## 2.3 Gerenciamento do Banco
-	Classe única (AppDatabase) que representa a conexão com o arquivo local do banco;
-	Padrão Singleton: apenas uma instância aberta durante toda a execução do app, evitando conflitos e desperdício de memória;
-	Controle de versão para permitir atualizações da estrutura sem perder dados.
## 2.4 Camada de Serviço — Repositório
-	Classe intermediária que separa o código do jogo das operações com o banco;
-	Todas as ações rodam em segundo plano (corrotinas), sem travar a tela nem o loop de animação do jogo;
-	Disponibiliza métodos simples: obterOuCriarUsuario(), registrarPartida(), carregarRanking().
## 2.5 Integração com o Fluxo do Jogo
-	Ao iniciar, identifica ou cria o jogador pelo nome;
-	Ao final de cada partida: salva o resultado e recalcula automaticamente os recordes e estatísticas do jogador;
-	Na tela de ranking: carrega e exibe a lista dos 10 mais bem colocados, ordenada pela maior pontuação.

## 3. Resultado Esperado
-	Dados permanecem salvos mesmo após fechar o aplicativo;
-	Código organizado, fácil manter e de expandir;
-	Interface sempre responsiva, sem travamentos;
-	Sistema de ranking funcional e atualizado em tempo real;


## Fontes: developer.android.google.cn/training/data-storage/room.html?hl=pt-br
