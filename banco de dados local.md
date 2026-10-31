## Por que escolhemos o Room Database?

## O Room é o banco de dados local oficial e recomendado pelo Google para desenvolvimento Android, sendo a escolha ideal para este projeto pelos seguintes motivos:

-	Alinhamento com a stack tecnológica: Integra nativamente com Kotlin, Android Studio e Gradle, sem exigir ferramentas externas ou bibliotecas de terceiros descontinuadas.
-	Facilidade e produtividade: Simplifica drasticamente o trabalho com dados em comparação ao SQLite puro — elimina a escrita de código repetitivo, conversão manual de valores e gerenciamento de cursores.
-	Segurança: Detecta erros de consulta em tempo de compilação, evitando falhas que só apareceriam durante o uso; combina perfeitamente com a proteção contra valores nulos do Kotlin.
-	Complementa o que já existe: O SharedPreferences continua sendo usado para configurações simples (cores, sons, tamanhos), enquanto o Room assume o armazenamento de dados estruturados e relacionais — como cadastro de jogadores, histórico de partidas e ranking.
-	Evolução garantida: Permite expandir facilmente o sistema com novas tabelas (conquistas, níveis salvos, estatísticas) sem reescrever a base.

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
