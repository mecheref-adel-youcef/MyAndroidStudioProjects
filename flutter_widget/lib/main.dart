import 'package:flutter/material.dart';

import 'app_screens/home.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: "Exploring UI widget",
        home: Scaffold(
            appBar: AppBar(
              title: Text("Widget App !"),
            ),
            body: MyLongListView(),
            floatingActionButton: FloatingActionButton(
            onPressed: ()=> debugPrint('FAB clicked'),
            child: Icon(Icons.add),
            tooltip: 'Add one more item',
        ),
        )
    );
  }
}

class MyBasicListView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: <Widget>[
        ListTile(
          leading: Icon(Icons.landscape),
          title: Text("Landscape"),
          subtitle: Text("beautiful view !"),
          trailing: Icon(Icons.wb_sunny),
          onTap: () => debugPrint("landscape tapped !"),
        ),
        ListTile(
          leading: Icon(Icons.laptop),
          title: Text("Linux"),
        ),
        ListTile(
          leading: Icon(Icons.phone),
          title: Text("Phone"),
        ),
        Text("Yet another element in list"),
      ],
    );
  }
}

class MyLongListView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var myList = getListElements();
    return ListView.builder(
        itemCount: myList.length,
        itemBuilder: (context, index) {
          return ListTile(
            leading: Icon(Icons.arrow_right),
            title: Text(myList[index]),
            onTap: (){
              debugPrint('${myList[index]} was tapped !');
              showSnackBar(context, myList[index]);
            },
            subtitle: Text("beautiful view !"),

          );
        });
  }

  List<String> getListElements() {
    var items = List<String>.generate(100, (counter) => "Item $counter");
    return items;
  }

  void showSnackBar(BuildContext context,String item){
    var snackBar = SnackBar(
        content: Text("you just tapped $item"),
        action: SnackBarAction(label: "UNDO", onPressed: ()=> debugPrint("performing UNDO operation")),
    );
    Scaffold.of(context).showSnackBar(snackBar);
  }
}
