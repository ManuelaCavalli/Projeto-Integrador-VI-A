package com.example.brickbreaker

import android.graphics.Color
import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.media.MediaPlayer
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    // Elementos visuais da tela do jogo
    private lateinit var scoreText: TextView
    private lateinit var paddle: View
    private lateinit var ball: View
    private lateinit var brickContainer: LinearLayout

    // Posição atual da bola
    private var ballX = 0f
    private var ballY = 0f

    // Velocidade da bola nos eixos X e Y
    private var ballSpeedX = 0f
    private var ballSpeedY = 0f

    // Posição horizontal da raquete
    private var paddleX = 0f

    // Pontuação do jogador
    private var score = 0

    // Quantidade de linhas e colunas de blocos
    private val brickRows = 9
    private val brickColumns = 10

    // Dimensões dos blocos
    private val brickMargin = 4

    // Quantidade de vidas disponíveis
    private var lives = 5

    // Nível atual
    private var currentLevel = 1
    private var totalBricks = 0

    // Configurações
    private var brickColorOption = 0
    private var brickSizeOption = 1

    // Objetos responsáveis pelos efeitos sonoros
    private var startSound: MediaPlayer? = null
    private var brickHitSound: MediaPlayer? = null
    private var paddleHitSound: MediaPlayer? = null

    private var animator: ValueAnimator? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Oculta a barra de status e a barra de navegação
        // para que o jogo utilize praticamente toda a tela.
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )

        // Carrega o layout XML da tela do jogo
        setContentView(R.layout.activity_game)

        // Carrega as configurações
        val preferences = getSharedPreferences("brickbreaker_settings", Context.MODE_PRIVATE)
        brickColorOption = preferences.getInt("color", 0)
        brickSizeOption = preferences.getInt("size", 1)

        // Carrega os arquivos de áudio armazenados na pasta res/raw
        startSound = MediaPlayer.create(this, R.raw.start)
        brickHitSound = MediaPlayer.create(this, R.raw.ball)
        paddleHitSound = MediaPlayer.create(this, R.raw.ball)

        // Localiza os elementos do layout através dos seus IDs
        scoreText = findViewById(R.id.scoreText)
        paddle = findViewById(R.id.paddle)
        ball = findViewById(R.id.ball)
        brickContainer = findViewById(R.id.brickContainer)

        // Localiza o botão responsável por iniciar uma nova partida
        val newgame = findViewById<Button>(R.id.newgame)

        // Define o que acontece quando o jogador pressiona "Novo Jogo"
        newgame.setOnClickListener {
            currentLevel = 1
            score = 0
            lives = 5
            startLevel()
            newgame.visibility = View.INVISIBLE
        }
    }

    private fun startLevel() {
        scoreText.text = "Level $currentLevel - Score: $score"
        initializeBricks(currentLevel)
        startSound?.start()
        start()
    }

    private fun initializeBricks(level: Int) {
        brickContainer.removeAllViews()
        totalBricks = 0

        // Ajusta o tamanho baseado na configuração
        val actualBrickWidth = when(brickSizeOption) {
            0 -> 80
            1 -> 100
            2 -> 130
            else -> 100
        }
        val actualBrickHeight = when(brickSizeOption) {
            0 -> 30
            1 -> 40
            2 -> 50
            else -> 40
        }

        for (row in 0 until brickRows) {
            val rowLayout = LinearLayout(this)
            rowLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            for (col in 0 until brickColumns) {
                // Lógica de padrão por nível fixa e conforme requisitos
                val shouldCreateBrick = when (level) {
                    1 -> true // Nível 1: Todos os blocos (Parede cheia)
                    2 -> (row + col) % 2 == 0 // Nível 2: Xadrez
                    3 -> col >= row && col < brickColumns - row // Nível 3: Pirâmide
                    4 -> col % 2 == 0 // Nível 4: Colunas
                    5 -> row % 2 == 0 || col % 2 == 0 // Nível 5: Grade vazada
                    else -> true
                }

                if (shouldCreateBrick) {
                    val brick = View(this)
                    val brickParams = LinearLayout.LayoutParams(actualBrickWidth, actualBrickHeight)
                    brickParams.setMargins(brickMargin, brickMargin, brickMargin, brickMargin)
                    brick.layoutParams = brickParams

                    // Cores baseadas na configuração
                    val color = when(brickColorOption) {
                        0 -> getClassicColor(row) // Clássico
                        1 -> getRainbowColor(row, col)
                        2 -> Color.BLUE
                        3 -> Color.RED
                        4 -> Color.GREEN
                        else -> Color.GRAY
                    }
                    brick.setBackgroundColor(color)
                    rowLayout.addView(brick)
                    totalBricks++
                } else {
                    val emptyView = View(this)
                    val emptyParams = LinearLayout.LayoutParams(actualBrickWidth, actualBrickHeight)
                    emptyParams.setMargins(brickMargin, brickMargin, brickMargin, brickMargin)
                    emptyView.layoutParams = emptyParams
                    emptyView.visibility = View.INVISIBLE
                    rowLayout.addView(emptyView)
                }
            }
            brickContainer.addView(rowLayout)
            // Garante que o nível tenha pelo menos um bloco
        }
        if (totalBricks == 0) {
            initializeBricks(level)
            return
        }
    }

    private fun getClassicColor(row: Int): Int {
        return when (row) {
            0 -> Color.RED
            1 -> Color.rgb(255, 165, 0)
            2 -> Color.YELLOW
            3 -> Color.GREEN
            4 -> Color.CYAN
            5 -> Color.BLUE
            6 -> Color.MAGENTA
            7 -> Color.RED
            8 -> Color.YELLOW
            else -> Color.WHITE
        }
    }

    private fun getRainbowColor(row: Int, col: Int): Int {
        val hsv = floatArrayOf(((row * 10 + col * 10) % 360).toFloat(), 0.8f, 0.9f)
        return Color.HSVToColor(hsv)
    }

    private fun moveBall() {
        ballX += ballSpeedX
        ballY += ballSpeedY
        ball.x = ballX
        ball.y = ballY
    }

    private fun movePaddle(x: Float) {
        paddleX = x - paddle.width / 2
        paddle.x = paddleX
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun checkCollision() {
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()

        if (ballX <= 0 || ballX + ball.width >= screenWidth) {
            ballSpeedX *= -1
        }

        if (ballY <= 0) {
            ballSpeedY *= -1
        }

        if (
            ballY + ball.height >= paddle.y &&
            ballY + ball.height <= paddle.y + paddle.height &&
            ballX + ball.width >= paddle.x &&
            ballX <= paddle.x + paddle.width
        ) {
            paddleHitSound?.seekTo(0)
            paddleHitSound?.start()
            ballSpeedY *= -1
            scoreText.text = "Level $currentLevel - Score: $score"
        }

        if (ballY + ball.height >= screenHeight) {
            handleBallLoss()
            return
        }

        for (row in 0 until brickRows) {
            val rowLayout = brickContainer.getChildAt(row) as? LinearLayout ?: continue
            val rowTop = rowLayout.y + brickContainer.y

            for (col in 0 until brickColumns) {
                val brick = rowLayout.getChildAt(col) ?: continue

                if (brick.visibility == View.VISIBLE) {
                    val brickLeft = brick.x + rowLayout.x
                    val brickRight = brickLeft + brick.width
                    val brickTop = brick.y + rowTop
                    val brickBottom = brickTop + brick.height

                    if (ballX + ball.width >= brickLeft && ballX <= brickRight &&
                        ballY + ball.height >= brickTop && ballY <= brickBottom
                    ) {

                        brickHitSound?.seekTo(0)
                        brickHitSound?.start()

                        // Remove o bloco
                        brick.visibility = View.INVISIBLE

                        // Faz a bola rebater
                        ballSpeedY *= -1

                        // Atualiza a pontuação
                        score++
                        totalBricks--

                        scoreText.text = "Level $currentLevel - Score: $score"

                        // Se não existem mais blocos, termina o nível
                        if (totalBricks == 0) {
                            animator?.cancel()
                            checkWin()
                            return
                        }
                    }
                }
            }
        }
    }

    private fun handleBallLoss() {
        animator?.cancel()

        // Perde uma vida
        lives--

        // Sempre pergunta ao jogador o que deseja fazer
        showLoseDialog()
    }

    private fun showLoseDialog() {
        AlertDialog.Builder(this)
            .setTitle("Você perdeu a bola!")
            .setMessage("Você ainda possui $lives vidas.\n\nO que deseja fazer?")
            .setCancelable(false)
            .setPositiveButton("Reiniciar Nível") { _, _ ->
                startLevel()
            }
            .setNegativeButton("Próximo Nível") { _, _ ->
                currentLevel++

                if (currentLevel <= 5) {
                    startLevel()
                } else {
                    showVictoryDialog()
                }
            }
            .setNeutralButton("Sair") { _, _ ->
                finish()
            }
            .show()
    }

    private fun checkWin() {
        // Transição automática para o próximo nível conforme requisito (c)
        Toast.makeText(this, "Nível $currentLevel concluído! Iniciando o próximo...", Toast.LENGTH_SHORT).show()
        
        currentLevel++
        if (currentLevel <= 5) {
            startLevel()
        } else {
            // Vitória Final
            showVictoryDialog()
        }
    }

    private fun showVictoryDialog() {
        AlertDialog.Builder(this)
            .setTitle("Parabéns!")
            .setMessage("Você concluiu todos os níveis e venceu o jogo!\n\nO que deseja fazer?")
            .setCancelable(false)
            .setPositiveButton("Jogar Novamente") { _, _ ->
                currentLevel = 1
                score = 0
                lives = 5
                startLevel()
            }
            .setNegativeButton("Sair") { _, _ ->
                finish()
            }
            .show()
    }

    private fun gameOver() {
        animator?.cancel()
        scoreText.text = "Game Over"
        val newgame = findViewById<Button>(R.id.newgame)
        newgame.visibility = View.VISIBLE
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun movepaddle() {
        paddle.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    movePaddle(event.rawX)
                }
            }
            true
        }
    }

    private fun start() {
        movepaddle()
        val displayMetrics = resources.displayMetrics
        val screenDensity = displayMetrics.density
        val screenWidth = displayMetrics.widthPixels.toFloat()
        val screenHeight = displayMetrics.heightPixels.toFloat()

        paddleX = screenWidth / 2 - paddle.width / 2
        paddle.x = paddleX

        ballX = screenWidth / 2 - ball.width / 2
        ballY = screenHeight / 2 - ball.height / 2 + 300 // Posição inicial melhor

        ballSpeedX = 4 * screenDensity
        ballSpeedY = -4 * screenDensity

        animator?.cancel()
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator?.duration = Long.MAX_VALUE
        animator?.interpolator = LinearInterpolator()
        animator?.addUpdateListener {
            moveBall()
            checkCollision()
        }
        animator?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        animator?.cancel()
        startSound?.release()
        brickHitSound?.release()
        paddleHitSound?.release()
    }
}