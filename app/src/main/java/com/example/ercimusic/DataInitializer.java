package com.example.ercimusic;

import android.content.Context;
import com.example.ercimusic.models.Level;
import com.example.ercimusic.models.Question;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {

    public static void initializeData(Context context) {
        // No necesitamos inicializar nada aquí, solo proporcionar los datos
    }

    public static List<Level> getNivelesIniciales() {
        List<Level> niveles = new ArrayList<>();
        niveles.add(new Level(1, 0));
        niveles.add(new Level(2, 5));
        niveles.add(new Level(3, 10));
        niveles.add(new Level(4, 15));
        niveles.add(new Level(5, 20));
        niveles.add(new Level(6, 25));
        return niveles;
    }

    public static List<Question> getPreguntasIniciales() {
        List<Question> preguntas = new ArrayList<>();

        // Nivel 1
        preguntas.add(new Question(1, 1, "",
                "Money - Pink Floyd", "Money - Pink Floyd", "Religious Experience - Kevin Ayers",
                "Mr.Blue - Clear Light", "Lucky - Lucky Twice", "01-1"));
        preguntas.add(new Question(2, 1, "",
                "Shooting Star - Bag Riders", "Love Song - Syd Barret", "Time To Turn - Eloy",
                "Shooting Star - Bag Riders", "Friction - The Midnight", "02-1"));
        preguntas.add(new Question(3, 1, "",
                "Judas - Lady gaga", "Heart Of Glass - Miley Cyrus", "The Shoop Shoop Song - Cher",
                "Gimme More - Britney Spears", "Judas - Lady gaga", "03-1"));
        preguntas.add(new Question(4, 1, "",
                "Melendi - Piratas del Bar Caribe", "Melendi - Piratas del Bar Caribe", "El universo Sobre Mi - Amaral",
                "Como Camarón - Estopa", "La casa por el tejado - Fito y Fitipaldis", "04-1"));
        preguntas.add(new Question(5, 1, "",
                "smells like teen spirit - nirvana", "change - deftones", "smells like teen spirit - nirvana",
                "killing in the name - rage against the machine", "bitter sweet symphony - the verve", "05-1"));

        // Nivel 2
        preguntas.add(new Question(6, 2, "",
                "let it be - the beatles", "sultans of swing - dire straits", "start me up - the rolling stones",
                "heart of gold - neil young", "let it be - the beatles", "06-2"));
        preguntas.add(new Question(7, 2, "",
                "black hole sun - soundgarden", "enter sandman - metallica", "black hole sun - soundgarden",
                "no more tears - ozzy osbourne", "blind - korn", "07-2"));
        preguntas.add(new Question(8, 2, "",
                "creep - radiohead", "drain you - nirvana", "hey you - pink floyd",
                "duvet - boa", "creep - radiohead", "08-2"));
        preguntas.add(new Question(9, 2, "",
                "malabares - estopa", "besos - el canto del loco", "todo a cien - fito y fitipaldis",
                "estoy enfermo - pignoise", "malabares - estopa", "09-2"));
        preguntas.add(new Question(10, 2, "",
                "bad - michael jackson", "gangsta's paradise - coolio&L.V", "bad - michael jackson",
                "another day in paradise - phil collins", "dancing queen - abba", "10-2"));

        // Nivel 3
        preguntas.add(new Question(11, 3, "",
                "in the army now - status quo", "in the army now - status quo", "hold the line - toto",
                "invisible touch - genesis", "hot in the city - billy idol", "11-3"));
        preguntas.add(new Question(12, 3, "",
                "animal i have become - three days grace", "psychosocial - slipknot", "stricken - disturbed",
                "you're gonna go far kid - the offspring", "animal i have become - three days grace", "12-3"));
        preguntas.add(new Question(13, 3, "",
                "im not down - the clash", "eyes without a face - billy idol", "owner of a lonely heart - yes",
                "just like heaven - the cure", "im not down - the clash", "13-3"));
        preguntas.add(new Question(14, 3, "",
                "shout - tears for fears", "shout - tears for fears", "ordinary world - duran duran",
                "learning to fly - pink floyd", "love song - the cure", "14-3"));
        preguntas.add(new Question(15, 3, "",
                "break on through to the other side - the doors", "Purple Haze - Jimi Hendrix", "break on through to the other side - the doors",
                "Black Dog - Led Zeppelin", "Sunshine of Your Love - Cream", "15-3"));

        // Nivel 4
        preguntas.add(new Question(16, 4, "",
                "Buddy Holly - Weezer", "Just - Radiohead", "Today - The Smashing Pumpkins",
                "Everlong - Foo Fighters", "Buddy Holly - Weezer", "16-4"));
        preguntas.add(new Question(17, 4, "",
                "Layla - Derek & The Dominos", "Money for Nothing - Dire Straits", "Gimme Shelter - The Rolling Stones",
                "Layla - Derek & The Dominos", "More Than a Feeling - Boston", "17-4"));
        preguntas.add(new Question(18, 4, "",
                "Run - Joji", "Run - Joji", "Falling - Harry Styles",
                "Heather - Conan Gray", "The Night We Met - Lord Huron", "18-4"));
        preguntas.add(new Question(19, 4, "",
                "Would - Alice in Chains", "Black - Pearl Jam", "Plush - Stone Temple Pilots",
                "Fell on Black Days - Soundgarden", "Would - Alice in Chains", "19-4"));
        preguntas.add(new Question(20, 4, "",
                "Roundabout - Yes", "21st Century Schizoid Man - King Crimson", "Roundabout - Yes",
                "Aqualung - Jethro Tull", "The Musical Box - Genesis", "20-4"));

        // Nivel 5
        preguntas.add(new Question(21, 5, "",
                "All Along the Watchtower - Jimi Hendrix", "Sunshine of Your Love - Cream", "While My Guitar Gently Weeps - The Beatles",
                "All Along the Watchtower - Jimi Hendrix", "Mississippi Queen - Mountain", "21-5"));
        preguntas.add(new Question(22, 5, "",
                "Let Me Live My Life - Saint Asonia", "Let Me Live My Life - Saint Asonia", "I Stand Alone - Godsmack",
                "Fake It - Seether", "Bodies - Drowning Pool", "22-5"));
        preguntas.add(new Question(23, 5, "",
                "Octopus - Syd Barrett", "Octopus - Syd Barrett", "Care of Cell 44 - The Zombies",
                "Jennifer Juniper - Donovan", "Waterloo Sunset - The Kinks", "23-5"));
        preguntas.add(new Question(24, 5, "",
                "Flor Venenosa - Héroes del Silencio", "Apuesta por el rock and roll - Más Birras", "El roce de tu cuerpo - Platero y Tú",
                "Flor Venenosa - Héroes del Silencio", "Maneras de vivir - Leño", "24-5"));
        preguntas.add(new Question(25, 5, "",
                "Las niñas de la Saye - Mojinos Escozíos", "Salta - Tequila", "El atraco - Burning",
                "Ojos de gata - Los Secretos", "Las niñas de la Saye - Mojinos Escozíos", "25-5"));

        // Nivel 6
        preguntas.add(new Question(26, 6, "",
                "Sneaky Creatures - Halou", "Teardrop - Massive Attack", "Glory Box - Portishead",
                "6 Underground - Sneaker Pimps", "Sneaky Creatures - Halou", "26-6"));
        preguntas.add(new Question(27, 6, "",
                "Embrujada - Tino Casal", "Ni tú ni nadie - Alaska y Dinarama", "Escuela de calor - Radio Futura",
                "En algún lugar - Duncan Dhu", "Embrujada - Tino Casal", "27-6"));
        preguntas.add(new Question(28, 6, "",
                "Browser History - Graham Kartna", "Money Machine - 100 gecs", "Browser History - Graham Kartna",
                "Click - Charli XCX", "BIPP - SOPHIE", "28-6"));
        preguntas.add(new Question(29, 6, "",
                "The Great American Eagle Tragedy - Earth Opera", "White Bird - It's a Beautiful Day", "The Great American Eagle Tragedy - Earth Opera",
                "Get Together - The Youngbloods", "In the Year 2525 - Zager & Evans", "29-6"));
        preguntas.add(new Question(30, 6, "",
                "Always Look on the Bright Side of Life - Monty Python", "I'm Gonna Be (500 Miles) - The Proclaimers", "Come and Get Your Love - Redbone",
                "Always Look on the Bright Side of Life - Monty Python", "Don't Worry Be Happy - Bobby McFerrin", "30-6"));

        return preguntas;
    }
}