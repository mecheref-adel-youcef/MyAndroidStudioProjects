import 'package:flutter/material.dart';

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
          padding: EdgeInsets.only(left: 10.0, top: 40.0),
          alignment: Alignment.center,
          color: Colors.cyan,
          child: Column(
            children: <Widget>[
              MyRow(title: "My facebook", description: "joseph youcefff"),
              MyRow(title: "My instagram", description: "joseph_youcef198"),
              Image.asset(
                'images/fb.png',
                width: 100.0,
                height: 100.0,
              ),
              MyBtn()
            ],
          )),
    );
  }
}

class MyRow extends StatelessWidget {
  final String title;
  final String description;

  const MyRow({Key key, this.title, this.description}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[
        Expanded(
            child: Text(
          this.title,
          style: TextStyle(
              decoration: TextDecoration.none,
              fontSize: 25.0,
              fontFamily: 'Raleway',
              fontWeight: FontWeight.w300,
              color: Colors.white),
        )),
        Expanded(
            child: Text(
          this.description,
          style: TextStyle(
              decoration: TextDecoration.none,
              fontSize: 20.0,
              fontFamily: 'Raleway',
              fontWeight: FontWeight.w300,
              color: Colors.white),
        )),
      ],
    );
  }
}

class MyBtn extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 30.0),
      width: 250.0,
      height: 50.0,
      child: RaisedButton(
          color: Colors.pinkAccent,
          child: Text(
            "Click me",
            style: TextStyle(
              fontSize: 20.0,
              fontFamily: 'Raleway',
              fontWeight: FontWeight.w700,
            ),
          ),
          elevation: 6.0,
          textColor: Colors.white,
          onPressed: () => btnClicked(context)),
    );
  }

  void btnClicked(BuildContext context) {
    var alertDialog = AlertDialog(
      title: Text("Btn has clicked successfully"),
      content: Text("Have a good night i have to go to sleep now !"),
    );

    showDialog(
        context: context, builder: (BuildContext context) => alertDialog);
  }
}
