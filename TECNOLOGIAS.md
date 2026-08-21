# Escolha do Ambiente e Tecnologias

Este documento detalha as escolhas técnicas feitas pela equipe para o desenvolvimento do projeto **Brick Breaker**.

## 1. Ambiente de Desenvolvimento (IDE)
Utilizamos o Android Studio (versão Ladybug 2024.2.1 ou superior). 
- É o ambiente oficial para desenvolvimento Android, oferecendo integração completa com o Gradle, ferramentas de debug avançadas, emuladores e suporte nativo às APIs mais recentes do sistema operacional.

## 2. Linguagem de Programação
A linguagem utilizada é o Kotlin.
- O Kotlin é a linguagem recomendada pelo Google para o desenvolvimento Android moderno. Suas principais vantagens incluem a segurança contra erros de ponteiro nulo (*null safety*), sintaxe concisa que reduz o código boilerplate e total interoperabilidade com bibliotecas Java legadas.

## 3. Tecnologias e Bibliotecas
- View System (XML): Optamos por utilizar layouts XML tradicionais para a interface do usuário, facilitando o controle preciso sobre os componentes de tela e o Modo Imersivo.
- ValueAnimator: Utilizado para criar o loop principal do jogo e a animação fluida da bola.
- MediaPlayer: Biblioteca nativa para a reprodução de efeitos sonoros de colisão e início de jogo.
- SharedPreferences: Utilizada para persistir as configurações de cores e tamanhos de blocos escolhidas pelo usuário.

## 4. Geração do Arquivo APK
O processo de build é gerenciado pelo 'Gradle'. Para gerar o arquivo APK final para entrega, seguimos os passos abaixo:

1. No menu superior do Android Studio, acesse: `Build` > `Build Bundle(s) / APK(s)` > `Build APK(s)`.
2. O Gradle processará os recursos e compilará o código Kotlin.
3. Ao finalizar, uma notificação aparecerá no canto inferior direito.
4. O arquivo gerado estará localizado em: `app/build/outputs/apk/debug/app-debug.apk`.


