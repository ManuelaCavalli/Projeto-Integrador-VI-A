# 🗺️ Mapeamento de Wireframe e Fluxo do Usuário (User Flow)

Para evitar apenas a exibição de telas jogadas e garantir a correta arquitetura de informação do jogo **Brick Breaker**, este documento mapeia a jornada de navegação do usuário através de nossos wireframes de alta definição.

## 🧭 Mapa Geral do Fluxo de Telas
A imagem abaixo ilustra como todas as interfaces se conectam logicamente. Cada ação do usuário (clique em botões ou transições automáticas) aponta diretamente para a sua respectiva tela destino.

![Fluxo Completo de Telas](./wireframe_fluxo.png)

---

## 📐 Detalhamento Estrutural das Interfaces

### 1. Menu Principal
* **Propósito:** Ponto de entrada centralizado do aplicativo.
* **Elementos de Interface:**
  * Título em destaque superior (`BRICK BREAKER`).
  * Botão `Integrantes` -> Abre a listagem da equipe.
  * Botão `Iniciar Jogo` -> Encaminha para a validação de clique antes da partida.
  * Botão `Configurações` -> Abre o painel de preferências físicas e visuais.

### 2. Painel de Integrantes
* **Propósito:** Exibir os créditos e os desenvolvedores do projeto.
* **Elementos de Interface:**
  * Lista vertical de identificação com os nomes dos membros acadêmicos.
  * Botão de rodapé `Voltar` -> Retorna o usuário ao Menu Principal preservando o estado do app.

### 3. Painel de Configurações (Menus Suspensos)
* **Propósito:** Permitir customizações de jogo sem poluir a tela principal.
* **Elementos de Interface:**
  * **Seletor 1 (Padrão de Cores):** Dropdown que exibe em camada sobreposta (*overlay*) as opções visuais (`Clássico`, `Arco-íris`, `Azul`, `Vermelho`, `Verde`).
  * **Seletor 2 (Tamanho dos Tijolos):** Dropdown para ajustes de jogabilidade (`Pequeno`, `Médio`, `Grande`).
  * Botão `Salvar` -> Confirma as escolhas e updates as preferências do sistema.
  * Botão `Voltar` -> Descarta mudanças não salvas e retorna à raiz do jogo.

### 4. Transição de Partida (Toque para Iniciar)
* **Propósito:** Prevenir começos acidentais e preparar o jogador para o posicionamento da raquete.
* **Elementos de Interface:**
  * Botão flutuante central de clique único (`Toque para Iniciar`).
  * Indicador opaco da barra de rebote na base inferior.

### 5. Interface da Partida (Gameplay & Variações de Níveis)
* **Propósito:** Ambiente ativo de interação e mecânica do jogo.
* **Elementos de Interface:**
  * Matriz dinâmica superior de blocos quebráveis (adaptável conforme o tamanho e o espaçamento escolhido nas configurações).
  * Elemento móvel circular/quadrado representando a Esfera (Bola).
  * Barra de controle deslizante horizontal controlada pelo usuário.

### 6. Modal de Feedback (Pop-Up / Tela de Perda)
* **Propósito:** Notificar interrupção imediata de progresso de forma contextualizada sobre a partida.
* **Elementos de Interface:**
  * Fundo escurecido que congela a física da partida original por trás do alerta.
  * Caixa branca flutuante (*Modal*) com exibição dinâmica de vidas restantes.
  * Três botões de escolha direta: `Sair` (Limpa estado), `Próximo Nível` (Avanço manual) e `Reiniciar Nível` (Reseta posições).
