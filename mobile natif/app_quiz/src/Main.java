import 'package:flutter/app_quiz';

void main() {
    runApp(QuizApp());
}

class QuizApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
                title: 'Quiz App',
                theme: ThemeData(primarySwatch: Colors.blue),
        home: QuizPage(),
    );
    }
}

class QuizPage extends StatefulWidget {
    @override
    _QuizPageState createState() => _QuizPageState();
}

class _QuizPageState extends State<QuizPage> {
    int currentQuestionIndex = 0;
    int score = 0;
    bool isQuizCompleted = false;

    final List<Map<String, Object>> questions = [
    {
        "question": "Quelle est la planète la plus proche du Soleil ?",
            "answers": ["Terre", "Vénus", "Mercure", "Mars"],
        "correct Answer": "Mercure"
    }
    {
        "question": "Qui a peint la Joconde ?",
            "answers": ["Léonard de Vinci", "Pablo Picasso", "Vincent Van Gogh", "Claude Monet"],
        "correctAnswer": "Léonard de Vinci"
    },
    {
        "question": "Quel est le fleuve le plus long du monde ?",
            "answers": ["Amazone", "Nil", "Mississippi", "Yangtsé"],
        "correctAnswer": "Nil"
    }
  ];

    void validateAnswer(String selectedAnswer) {
        String correctAnswer = questions[currentQuestionIndex]['correctAnswer'] as String;

        if (selectedAnswer == correctAnswer) {
            setState(() {
                score++;
            });
        }

        if (currentQuestionIndex < questions.length - 1) {
            setState(() {
                currentQuestionIndex++;
            });
        } else {
            setState(() {
                isQuizCompleted = true;
            });

        }
    }

    void resetQuiz() {
        setState(() {
            score = 0;
            currentQuestionIndex = 0;
            isQuizCompleted = false;
        });
    }

    @override
    Widget build(BuildContext context) {
        return Scaffold(
                appBar: AppBar(
                title: Text('Quiz App'),
      ),
        body: isQuizCompleted
                ? Center(
                child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
        Text(
                'Your Score: $score/${questions.length}',
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                  ),
        SizedBox(height: 20),
        ElevatedButton(
                onPressed: resetQuiz,
                child: Text('Restart Quiz'),
                  )
                ],
              ),
            )
          : Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
        Padding(
                padding: const EdgeInsets.all(16.0),
                child: Text(
                questions[currentQuestionIndex]['question'] as String,
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                  ),
                ),
                ...(questions[currentQuestionIndex]['answers'] as List<String>).map((answer) {
        return ElevatedButton(
                onPressed: () => validateAnswer(answer),
                child: Text(answer),
                  );
                }).toList(),
              ],
            ),
    );
    }
}
