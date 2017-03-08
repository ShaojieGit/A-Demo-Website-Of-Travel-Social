var boxid;

function showlightbox(id,src) {
	
	var idpicture = document.getElementById(id);
	idpicture.style.display = 'none';
	 boxid = id+'1';
	var bigid = id+'2';
	var box = document.getElementById(boxid);
	box.style.display = 'block';
	
	var img = document.getElementById(bigid);
	img.src = src;
	fade_in2();
	fade_out1();
}

function closelightbox() {
	var box = document.getElementById(boxid);
	box.style.display = 'none';
	idpicture.style.display = 'block';
	fade_in1();
	fade_out2();
}

function fade_in1() {
	$('h1').fadeIn('slow');
}

function fade_out1() {
	$('h1').fadeOut('slow');
}

function fade_in2() {
	$('h2').fadeIn('slow');
}

function fade_out2() {
	$('h2').fadeOut('slow');
}

/*
   $(document).ready(function(){   

           // 璁╁脊鍑虹獥鍙ｅ眳涓�
     function center(obj) {
        
         var screenWidth = $(window).width(), screenHeight = $(window).height();  //褰撳墠娴忚鍣ㄧ獥鍙ｇ殑 瀹介珮
         var scrolltop = $(document).scrollTop();//鑾峰彇褰撳墠绐楀彛璺濈椤甸潰椤堕儴楂樺害
    
         var objLeft = (screenWidth - obj.width())/2 ;
         var objTop = (screenHeight - obj.height())/2 + scrolltop;
 
         obj.css({left: objLeft + 'px', top: objTop + 'px'});

         //娴忚鍣ㄧ獥鍙ｅぇ灏忔敼鍙樻椂
         $(window).resize(function() {
             screenWidth = $(window).width();
             screenHeight = $(window).height();
             scrolltop = $(document).scrollTop();
            
             objLeft = (screenWidth - obj.width())/2 ;
             objTop = (screenHeight - obj.height())/2 + scrolltop;
            
             obj.css({left: objLeft + 'px', top: objTop + 'px'});
            
         });

         //娴忚鍣ㄦ湁婊氬姩鏉℃椂鐨勬搷浣溿�
         $(window).scroll(function() {
             screenWidth = $(window).width();
             screenHeight = $(window).height();
             scrolltop = $(document).scrollTop();
            
             objLeft = (screenWidth - obj.width())/2 ;
             objTop = (screenHeight - obj.height())/2 + scrolltop;
            
             obj.css({left: objLeft + 'px', top: objTop + 'px'});
         });
        
     }

       $(".fa_sixin, .cuncui_tancu01").click(       
          function(){center( $('.private_message') )
           });

     

    
     
    
 });
 
 */