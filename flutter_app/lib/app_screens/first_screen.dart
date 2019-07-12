
import 'dart:math';

import 'package:flutter/material.dart';


class FirstScreen extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Center(
        child: Text(
          generateLuckyNumber(),
          style: TextStyle(fontSize: 30.0),
        ),
      ) ,
    );
  }

  String generateLuckyNumber(){
    var random = Random();
    int luckyNumber = random.nextInt(10); // 0 to 9
    return "Your lucky number is $luckyNumber";
  }

}

