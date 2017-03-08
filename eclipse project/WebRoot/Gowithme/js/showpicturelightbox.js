function showlightbox(src) {
	var box = document.getElementById('box');
	box.style.display = 'block';
	box.style.top = document.body.scrollTop;
	var img = document.getElementById('big');
	big.src = src;
}

function closelightbox() {
	var box = document.getElementById('box');
	box.style.display = 'none';
}


/*
   $(document).ready(function(){   

           // 让弹出窗口居中
     function center(obj) {
        
         var screenWidth = $(window).width(), screenHeight = $(window).height();  //当前浏览器窗口的 宽高
         var scrolltop = $(document).scrollTop();//获取当前窗口距离页面顶部高度
    
         var objLeft = (screenWidth - obj.width())/2 ;
         var objTop = (screenHeight - obj.height())/2 + scrolltop;
 
         obj.css({left: objLeft + 'px', top: objTop + 'px'});

         //浏览器窗口大小改变时
         $(window).resize(function() {
             screenWidth = $(window).width();
             screenHeight = $(window).height();
             scrolltop = $(document).scrollTop();
            
             objLeft = (screenWidth - obj.width())/2 ;
             objTop = (screenHeight - obj.height())/2 + scrolltop;
            
             obj.css({left: objLeft + 'px', top: objTop + 'px'});
            
         });

         //浏览器有滚动条时的操作、
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