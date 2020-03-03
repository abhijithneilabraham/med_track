import 'package:flutter/material.dart';
import 'package:med_track/login_page.dart';
void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Supplier App',
      theme: ThemeData(
        primarySwatch: Colors.green ,
      ),
      home: LoginPage(),
    );
  }
}
