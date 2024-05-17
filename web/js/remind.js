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
        x=document.getElementById("start");
        x.href="../html/answer.html";
      }
      function changelink2(){
        x=document.getElementById("start");
        x.href="javascript:show1()";
      }
