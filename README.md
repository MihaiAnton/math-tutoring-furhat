# math-tutoring-furhat
Conversational agents assignment: math tutoring chatbot with anger management

## Emotion Recognition
The user's emotion can be detected through an external Python script. This script uses an older 
version of tensorflow: v1.8.0, which is only available via conda package manager (not pip). When 
`emotion_recognition.py` is run it sets up a local API on `http://127.0.0.1:5000/`. Add 
`get_emotion` to the URL to do a GET request from furhat to retrieve the valence and 
arousal values of the user trough a webcam image. Don't forget to slide the little cover off your 
webcam as I always do ;).

```
val response = khttp.get("http://127.0.0.1:5000/get_emotion").text
val values = response.split(' ')
val valence = values[0].toDouble()
val arousal = values[1].toDouble()
```
