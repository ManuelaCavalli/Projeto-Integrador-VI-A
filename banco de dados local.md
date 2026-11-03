## Por que escolhemos o SQLite?

## O SQLite é o banco de dados relacional leve, independente e padrão do sistema operacional Android, sendo a escolha ideal para este projeto pelos seguintes motivos:

- Suporte nativo e universal: Faz parte do núcleo do Android — não exige instalação de bibliotecas externas, não aumenta o tamanho do aplicativo e funciona em todos os dispositivos sem dependências adicionais.
- Controle total sobre os dados: Permite definir com precisão cada detalhe da estrutura, tipos de dados, restrições, índices e relações, sem camadas intermediárias que limitem ou escondam o funcionamento do banco.
- Compatibilidade e durabilidade: É a tecnologia de armazenamento local mais testada e estável da plataforma; código escrito para SQLite continua funcionando ao longo de versões e atualizações do Android sem risco de depreciação.
- Divisão clara de responsabilidades: O SharedPreferences continua sendo usado para configurações simples (cores, sons, preferências de exibição), enquanto o SQLite assume o armazenamento de dados estruturados, relacionais e em maior volume — como cadastro de jogadores, histórico de partidas e ranking.
- Evolução flexível: A estrutura pode ser ampliada com novas tabelas, campos e regras conforme o projeto cresce (conquistas, níveis salvos, estatísticas detalhadas), com total controle sobre as migrações de versão.

## Como será aplicado no projeto?

A implementação segue a arquitetura organizada em camadas, adaptada para o uso direto das APIs do SQLite no Android:

## 2.1 Estrutura de Dados — Entidades
Definem o que é armazenado no aparelho do usuário, com criação explícita por meio de comandos SQL:
-	Tabela usuarios: Cadastro de jogadores com nome, pontuação total, maior pontuação, quantidade de partidas jogadas e nível máximo alcançado.
-	Tabela pontuacoes: Registro de cada partida jogada, ligada ao jogador por meio de um identificador (usuarioId), com pontos obtidos, nível alcançado e data da partida.
-	Relacionamento estabelecido por chave estrangeira, garantindo integridade entre os registros de jogador e suas pontuações.
  
## 2.2 Acesso aos Dados — Operações com SQLite

Classe auxiliar que concentra todas as consultas e comandos, evitando repetição e centralizando a lógica:

- Inserir ou buscar jogador pelo nome;
- Registrar resultado de partida vinculada ao jogador;
- Consultar ranking ordenado pela maior pontuação, limitado aos 10 primeiros colocados;
- Retornar o histórico completo de partidas de um jogador específico;
- Atualizar automaticamente pontuações e estatísticas do jogador ao salvar uma nova partida.

## 2.3 Gerenciamento do Banco
- Classe BancoHelper que estende SQLiteOpenHelper — responsável por abrir a conexão, criar o arquivo do banco na primeira execução e gerenciar o ciclo de vida do armazenamento;
- Controle de versão implementado: ao alterar a estrutura, o método onUpgrade cuida de atualizar as tabelas preservando ou migrando os dados existentes;
- Gestão de conexões: controle de abertura e fechamento para evitar vazamentos de memória e conflitos de acesso.

## 2.4 Camada de Serviço — Repositório
- Classe intermediária que separa o código da interface e do jogo das operações diretas com o banco;
- Todas as consultas e gravações executam em linhas de execução em segundo plano, sem travar a tela nem o loop de animação do jogo;
- Disponibiliza métodos de uso simples: obterOuCriarUsuario(), registrarPartida(), carregarRanking(), ocultando os detalhes de comandos SQL e cursores.

## 2.5 Integração com o Fluxo do Jogo

- Ao iniciar o aplicativo: verifica se o jogador já existe pelo nome informado; se não existir, cria um novo registro;
- Ao final de cada partida: abre a conexão, insere o resultado na tabela de pontuações, atualiza os totais e recordes do jogador e encerra a operação;
- Na tela de ranking: executa a consulta ordenada, percorre o Cursor com os resultados e exibe a lista atualizada dos 10 melhores colocados.

## 3. Resultado Esperado
- Dados permanecem salvos no dispositivo mesmo após fechar e reiniciar o aplicativo;
- Estrutura transparente e totalmente controlável, facilitando manutenção e aprendizado sobre funcionamento de bancos de dados;
- Interface responsiva, com operações de banco executadas fora da thread principal;
- Sistema de ranking funcional, consistente e atualizado após cada partida;
- Base preparada para expansão com novas tabelas e regras conforme o jogo evolui.


## Fontes: ttps://developer.android.com/training/data-storage/sqlite?hl=pt_br
