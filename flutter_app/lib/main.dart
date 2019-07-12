import 'package:flutter/material.dart';

import 'app_screens/first_screen.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Welcome to Flutter',
      home: Scaffold(
        appBar: AppBar(
          title: Text("My First App Screen"),
        ),
        body: FirstScreen()
      ),
    );
  }
}