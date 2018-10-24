BLUR = false;
PULSATION = true;
PULSATION_PERIOD = 600;
PARTICLE_RADIUS = 4;

/* disable blur before using blink */
BLINK = false;
GLOBAL_PULSATION = false;
QUALITY = 2; /* 0 - 5 */
/* set false if you prefer rectangles */
ARC = true;
/* trembling + blur = fun */
TREMBLING = 0; /* 0 - infinity */
FANCY_FONT = "Arial";
BACKGROUND = "#000";
BLENDING = true;
/* if empty the text will be a random number */

var canvas;
var ctx;
var W,H;
var particles;

if (typeof Particle == "undefined" || !Particle){
   var Particle = {};
}

function onloadParticle(canvas){
    Particle.initParticles().draw();
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
    
        var tcanvas = document.createElement("canvas");
        var tctx = tcanvas.getContext("2d");
        tcanvas.width = W;
        tcanvas.height = H;
    
        total_area = W * H;
        total_particles = 2000;
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
    
            this.x = (i * area_length) % W;
            this.y = (i * area_length) / W * area_length;
    
            /* randomize delta to make particles sparkling */
            this.deltaOffset = Math.random() * PULSATION_PERIOD | 0;
    
            this.radius = 0.1 + Math.random() * 2;
        }        
        return this;
    };
})(window);

(function(){
    Particle.draw = function()
    {
        var now = Date.now();        
        ctx.globalCompositeOperation = "source-over";

        if (BLUR) ctx.globalAlpha = 0.1;
        else if (!BLUR && !BLINK) ctx.globalAlpha = 1.0;

        ctx.fillStyle = BACKGROUND;
        ctx.fillRect(0, 0, W, H)

        if (BLENDING) ctx.globalCompositeOperation = "lighter";

        for (var i = 0; i < particles.length; i++) {

            p = particles[i];

            /* in lower qualities there is not enough full pixels for all of  them - dirty hack*/

            if (isNaN(p.x)) continue

            ctx.beginPath();
            ctx.fillStyle = "rgb(" + p.r + ", " + p.g + ", " + p.b + ")";
            ctx.fillStyle = "rgba(" + p.r + ", " + p.g + ", " + p.b + ", " + p.alpha + ")";

            if (BLINK) ctx.globalAlpha = Math.sin(Math.PI * mod * 1.0);

            if (PULSATION) { /* this would be 0 -> 1 */
                var mod = ((GLOBAL_PULSATION ? 0 : p.deltaOffset) + now) % PULSATION_PERIOD / PULSATION_PERIOD;

                /* lets make the value bouncing with sinus */
                mod = Math.sin(mod * Math.PI);
            } else var mod = 1;

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
        return this;
    };
})();