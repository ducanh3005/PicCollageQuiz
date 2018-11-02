BLUR = false;
PULSATION = true;
PULSATION_PERIOD = 600;
PARTICLE_RADIUS = 4;

/* disable blur before using blink */
GLOBAL_PULSATION = false;
QUALITY = 2; /* 0 - 5 */
/* set false if you prefer rectangles */
ARC = true;
/* trembling + blur = fun */
TREMBLING = 0; /* 0 - infinity */
FANCY_FONT = "Arial";
BACKGROUND = "#000";
/* if empty the text will be a random number */

var canvas;
var ctx;
var tcanvas;
var tctx;
var W,H;
var particles;
var num = 0;
TEXTArray = ["Shadow","Neil"];
QUALITY_TO_FONT_SIZE = [10, 12, 40, 50, 100, 350];
QUALITY_TO_SCALE = [20, 6, 4, 2, 0.9, 0.5];
QUALITY_TO_TEXT_POS = [10, 20, 60, 100, 370, 280];

if (typeof Particle == "undefined" || !Particle){
   var Particle = {};
}
var godImage;

function onloadParticle(canvas){    
    var img = new Image();        
    img.onload = function() {
        var tcanvas2 = document.createElement("canvas");
        var tctx2 = tcanvas.getContext("2d"); 
        tcanvas2.width = window.innerWidth;
        tcanvas2.height = window.innerHeight;
        tctx2.drawImage(img,0,0,tcanvas2.width, tcanvas2.height)  
        godImage = tctx.getImageData(0,0,tcanvas2.width, tcanvas2.height);        
        for(var i = 0; i < godImage.data.length; i += 4) {
            godImage.data[i] = godImage.data[i] < 125 ? 255:0;
            godImage.data[i+1] = godImage.data[i+1] < 125 ? 255:0;
            godImage.data[i+2] = godImage.data[i+2] < 125 ? 255:0;
            godImage.data[i+3] = 255;
        }        
      };    
    img.src = 'god.png';    
    Particle.initParticles().MoveParticleToRandomPosition(TEXTArray[0]);    
    Particle.draw();
    setInterval(Particle.draw, 30);
    setInterval(Particle.CarouselText, 5000);
}

(function(window){
    Particle.initParticles = function()
    {        
        canvas = document.getElementById("canvas");
        ctx = canvas.getContext("2d");
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        W = canvas.width;
        H = canvas.height;
    
        tcanvas = document.createElement("canvas");
        tctx = tcanvas.getContext("2d");
        tcanvas.width = W;
        tcanvas.height = H;
    
        total_area = W * H;
        total_particles = 2500;
        single_particle_area = total_area / total_particles;
        area_length = Math.sqrt(single_particle_area);
    
        particles = [];
        for (var i = 1; i <= total_particles; i++) {
            particles.push(new particle(i));
        }
    
        function particle(i) {
            this.r = Math.round(Math.random() * 255|0);
            this.g = Math.round(Math.random() * 255|0);
            this.b = Math.round(Math.random() * 255|0);
            this.alpha = 1;
            this.vx = (Math.random()*2)-1;
            this.vy = (Math.random()*2-1);        
            this.x = Math.random()*window.innerWidth;
            this.y = Math.random()*window.innerHeight;
            this.move = true;            
            this.deltaOffset = Math.random() * PULSATION_PERIOD | 0;
            this.radius = 0.1 + Math.random() * 2;
        }        
        return this;
    };
})(window);

(function(){
    Particle.draw = function()
    {                
        ctx.globalCompositeOperation = "source-over";
        ctx.globalAlpha = 1.0;
        ctx.fillStyle = BACKGROUND;
        ctx.fillRect(0, 0, W, H)        
        ctx.globalCompositeOperation = "lighter";
        if(godImage != null){
            ctx.putImageData(godImage, 0, 0)  
        }
        
        //ctx.drawImage(godImage,0,0);        

        for (var i = 0; i < particles.length; i++) {            
            p = particles[i];     
            /* in lower qualities there is not enough full pixels for all of  them - dirty hack*/                   
            if (isNaN(p.x)) continue
            if(p.move){
                moveParticle(p);
            }else{
                drawParticle(p);
            }
            
        }         
        return this;
    };
    function drawParticle(p){        
        var now = Date.now();     
        ctx.beginPath();
        ctx.fillStyle = "rgba(" + p.r + ", " + p.g + ", " + p.b + ", " + p.alpha + ")";
        var mod = ((GLOBAL_PULSATION ? 0 : p.deltaOffset) + now) % PULSATION_PERIOD / PULSATION_PERIOD;
        /* lets make the value bouncing with sinus */
        mod = Math.sin(mod * Math.PI);
        var offset = TREMBLING ? TREMBLING * (-1 + Math.random() * 2) : 0;
        var radius = PARTICLE_RADIUS * p.radius;
        if (!ARC) {
            ctx.fillRect(offset + p.x - mod * radius / 2 | 0, offset + p.y - mod * radius / 2 | 0, radius * mod,
                radius * mod);
        } else {
            ctx.arc(offset + p.x | 0, offset + p.y | 0, radius * mod, Math.PI * 2, false);
            ctx.fill();
        }
        p.x += (p.dx - p.x) / 10;
        p.y += (p.dy - p.y) / 10;
   }
   function moveParticle(p){
        p.x += p.vx;
        p.y += p.vy;

        if( p.x > window.innerWidth ) {
            p.vx = -1-Math.random();
        }
        else if ( p.x < 0 ) {
            p.vx = 1+Math.random();
        }
        else {
            p.vx *= 1 + (Math.random()*0.005);
        }
        if( p.y > window.innerHeight ) {
            p.vy = -1-Math.random();
        }
        else if (p.y < 0 ) {
            p.vy = 1+Math.random();
        }
        else {
            p.vy *= 1 + (Math.random()*0.005);
        }        
        ctx.fillStyle = "rgba(" + p.r + ", " + p.g + ", " + p.b + ", " + p.alpha + ")";
        ctx.beginPath();    
        ctx.arc(p.x | 0, p.y | 0, PARTICLE_RADIUS * p.radius, Math.PI * 2, false);
        ctx.closePath();
        ctx.fill();
   }
})();



(function(){
    Particle.CarouselText = function(){
        if (num < TEXTArray.length - 1) {
            num++;
        } else {
            num = 0;
        }        
        return Particle.MoveParticleToRandomPosition(TEXTArray[num]);
    }
})();

(function(){
    Particle.MoveParticleToRandomPosition = function(TEXT)
    {        
        tctx.fillStyle = "white";
        tctx.fillRect(0, 0, W, H)    
        tctx.font = "bold " + QUALITY_TO_FONT_SIZE[QUALITY] + "px " + FANCY_FONT;
        tctx.fillStyle="#f00";     
        tctx.textBaseline = "top"   
        tctx.fillText(TEXT,Math.random() * 100, Math.random() * 100);        

        image_data = tctx.getImageData(0, 0, W, H);
        pixels = image_data.data;
        var positions = [];
        for (var i = 0; i < pixels.length; i = i + 2) {
            if (pixels[i] != 255) {
                position = {
                    x: (i / 2 % W | 0) * QUALITY_TO_SCALE[QUALITY] | 0,
                    y: (i / 2 / W | 0) * QUALITY_TO_SCALE[QUALITY] | 0
                }
                positions.push(position);
            }
        }
        return Particle.MoveParticletoDestinations(positions);
    }
})();

(function(){
    Particle.MoveParticletoDestinations = function(new_positions){
        var offset = Math.random() * particles.length;
        for (var i = 0; i < particles.length; i++) {
            var index = Math.floor((i + offset) % particles.length);            
            pa = particles[index];
            particles[index].alpha = 1;
            var distance = [];
            nearest_position = 0;
            if (new_positions.length) {
                for (var n = 0; n < new_positions.length; n++) {
                    po = new_positions[n];
                    distance[n] = Math.sqrt((pa.x - po.x) * (pa.x - po.x) + (pa.y - po.y) * (pa.y - po.y));
                    if (n > 0) {
                        if (distance[n] <= distance[nearest_position]) {
                            nearest_position = n;
                        }
                    }
                }
                particles[index].dx = new_positions[nearest_position].x;
                particles[index].dy = new_positions[nearest_position].y;
                particles[index].distance = distance[nearest_position];

                var po1 = new_positions[nearest_position];
                for (var n = 0; n < new_positions.length; n++) {
                    var po2 = new_positions[n];
                    distance = Math.sqrt((po1.x - po2.x) * (po1.x - po2.x) + (po1.y - po2.y) * (po1.y - po2.y));
                    if (distance <= 5) {
                        new_positions.splice(n, 1);
                    }
                }
                particles[index].move = false;
            } else {
                particles[index].move = true;
            }
        }
        return this;
    }
})(); 
