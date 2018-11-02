function onloadGod(canvas){    
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    var img = new Image();        
    img.onload = function() {
        var ctx = canvas.getContext('2d');    
        ctx.drawImage(img,0,0,window.innerWidth, window.innerHeight)  
        var imageData = ctx.getImageData(0,0,canvas.width, canvas.height);
        var data = imageData.data;
        for(var i = 0; i < data.length; i += 4) {
            imageData.data[i] = imageData.data[i] < 125 ? 255:0;
            imageData.data[i+1] = imageData.data[i+1] < 125 ? 255:0;
            imageData.data[i+2] = imageData.data[i+2] < 125 ? 255:0;
            imageData.data[i+3] = 255;
        }

        // overwrite original image
        ctx.putImageData(imageData, 0, 0)  
      };    
    img.src = 'god.png';
}