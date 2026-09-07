# Documentação dos Métodos de Construção

Este documento detalha a implementação lógica por trás da geração das fases e do controle físico do jogo.

## 🧱 Construção da Parede de Blocos
A geração dos blocos é feita de forma dinâmica através do método `initializeBricks(level)` na classe `GameActivity`. Utilizamos uma estrutura de loops aninhados que percorrem uma matriz lógica de linhas e colunas.

### Padrões de Níveis (Mínimo de 5)
Implementamos 5 algoritmos distintos para a criação das paredes:
1. **Nível 1 (Parede Completa):** Todos os blocos da matriz são renderizados.
2. **Nível 2 (Xadrez):** Utiliza a lógica `(row + col) % 2 == 0` para criar um padrão intercalado.
3. **Nível 3 (Pirâmide):** Lógica geométrica que limita as colunas baseada no índice da linha (`col >= row && col < columns - row`).
4. **Nível 4 (Colunas):** Gera apenas blocos em colunas pares (`col % 2 == 0`).
5. **Nível 5 (Grade):** Combinação de padrões de linhas e colunas para criar uma malha vazada.

## ⚡ Controle de Colisão
O controle de colisão é o núcleo da jogabilidade e foi implementado com as seguintes diretrizes:

- **Detecção Retangular:** A bola e cada bloco possuem coordenadas (`x`, `y`) e dimensões (`width`, `height`). A colisão é detectada quando há sobreposição dessas áreas.
- **Prevenção de "Atravessamento":** Para evitar que a bola destrua múltiplos blocos simultaneamente (requisito d), a lógica de verificação interrompe a execução (`return`) no exato momento em que o primeiro impacto é registrado, invertendo o vetor de velocidade imediatamente.

## ⚙️ Configurações Dinâmicas
As dimensões físicas dos tijolos (`brickWidth` e `brickHeight`) são calculadas em tempo de execução com base nas preferências salvas pelo usuário na tela de configurações, permitindo que a parede se adapte ao estilo de jogo escolhido (Pequeno, Médio ou Grande).
