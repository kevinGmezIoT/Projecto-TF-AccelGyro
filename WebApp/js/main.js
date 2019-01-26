var Acce1 = document.getElementById("Acce1");
var Acce1Ref = firebase.database().ref().child("X-accel");
var Acce2 = document.getElementById("Acce2");
var Acce2Ref = firebase.database().ref().child("Y-accel");
var Acce3 = document.getElementById("Acce3");
var Acce3Ref = firebase.database().ref().child("Z-accel");
var Gyro1 = document.getElementById("Gyro1");
var Gyro1Ref = firebase.database().ref().child("X-Gyros");
var Gyro2 = document.getElementById("Gyro2");
var Gyro2Ref = firebase.database().ref().child("Y-Gyros");
var Gyro3 = document.getElementById("Gyro3");
var Gyro3Ref = firebase.database().ref().child("Z-Gyros");


Acce1Ref.on('value', function(datasnapshot){ 
     Acce1.innerText = datasnapshot.val();
});

Acce2Ref.on('value', function(datasnapshot){ 
    Acce2.innerText = datasnapshot.val();
});

Acce3Ref.on('value', function(datasnapshot){
    Acce3.innerText = datasnapshot.val();
});

Gyro1Ref.on('value', function(datasnapshot){
    Gyro1.innerText = datasnapshot.val();
});

Gyro2Ref.on('value', function(datasnapshot){
    Gyro2.innerText = datasnapshot.val();

});

Gyro3Ref.on('value', function(datasnapshot){
    Gyro3.innerText = datasnapshot.val();
});
