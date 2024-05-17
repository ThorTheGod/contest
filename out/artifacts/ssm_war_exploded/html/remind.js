var remind = document.getElementById('remind')
    remind.style.display = 'none';
    function show1()
    {
        remind.style.display = 'block';
    }
    function hide1()
    {
        remind.style.display = 'none';
    }

    function changelink(){
        x=document.getElementById("btn1");
        x.href="mainweb-login.html";
      }
      function changelink2(){
        x=document.getElementById("btn1");
        x.href="javascript:show1()";
      }
