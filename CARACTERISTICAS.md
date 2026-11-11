# 4 CARACTERÍSTICAS DO APLICATIVO

As funcionalidades do aplicativo **Brick Breaker** são descritas a seguir.

## 4.1 Identificação do jogador

O sistema deve solicitar o nome do jogador ao iniciar e verificar sua existência no banco de dados local. Caso o registro exista, os dados do jogador devem ser carregados; caso contrário, um novo registro deve ser criado.

## 4.2 Menu principal

O sistema deve disponibilizar uma tela inicial com acesso às funções Iniciar Jogo, *Ranking*, Configurações e Integrantes.

## 4.3 Início controlado da partida

O sistema deve exibir a instrução "Toque para Iniciar" antes de cada partida, de modo a evitar o início acidental.

## 4.4 Controle da raquete

O sistema deve permitir que o jogador movimente horizontalmente a raquete por meio do gesto de deslizar sobre a tela.

## 4.5 Colisão e pontuação

O sistema deve detectar as colisões entre a bola e os blocos, remover da tela os blocos atingidos e incrementar a pontuação do jogador.

## 4.6 Controle de vidas

O sistema deve decrementar a quantidade de vidas do jogador sempre que a bola ultrapassar o limite inferior da tela.

## 4.7 Progressão de níveis

O sistema deve disponibilizar cinco níveis, com padrões distintos de blocos, e avançar para o nível seguinte quando todos os blocos forem destruídos.

## 4.8 Janela de feedback

O sistema deve interromper a partida e exibir uma janela sobreposta com a quantidade de vidas restantes e as opções Sair, Próximo Nível e Reiniciar Nível.

## 4.9 Configuração de cores

O sistema deve permitir a seleção do padrão de cores dos blocos entre as opções Clássico, Arco-íris, Azul, Vermelho e Verde.

## 4.10 Configuração de tamanho

O sistema deve permitir a seleção do tamanho dos blocos entre as opções Pequeno, Médio e Grande.

## 4.11 Persistência das configurações

O sistema deve armazenar as configurações selecionadas, mantendo-as após o encerramento do aplicativo.

## 4.12 Registro de partidas

O sistema deve registrar no banco de dados local, ao término de cada partida, o jogador, a pontuação obtida, o nível alcançado e a data.

## 4.13 Atualização de estatísticas

O sistema deve atualizar a pontuação total, a maior pontuação, a quantidade de partidas e o nível máximo alcançado pelo jogador.

## 4.14 *Ranking*

O sistema deve exibir a classificação dos dez jogadores com maior pontuação registrados no dispositivo, em ordem decrescente.

## 4.15 Histórico de partidas

O sistema deve exibir a relação das partidas de um jogador, contendo pontuação, nível alcançado e data.

## 4.16 Tela de integrantes

O sistema deve exibir o nome completo de todos os integrantes do grupo, com opção de retorno ao menu principal.

## 4.17 Efeitos sonoros

O sistema deve reproduzir efeitos sonoros no início da partida e nas colisões da bola.
