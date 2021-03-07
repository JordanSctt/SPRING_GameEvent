package fr.greta.java;

import fr.greta.java.game.persistence.entity.GameEntity;
import fr.greta.java.game.persistence.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class VideoGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoGameApplication.class, args);
	}


	@Autowired
	private GameRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		if(repository.count() == 0) {
			save("Life is Feudal", "MMO / RPG / Medieval / Open-World");
			save("Conqueror's Blade", "MMO / RPG / Medieval / Open-World");
			save("Counter-Strike: Global Offensive", "FPS / Multi-joueur");
			save("DayZ", "Survie / Multi-joueur / Open-World");
			save("Mist Survival", "Survie / Solo / Aventure");
			save("Mordhau", "Multi-joueur / Medieval");
			save("Mount & Blade: Warband", "RPG / Medieval / Aventure / Open-World");
			save("Night of the Dead", "Tower Defense / Coop / Action");
			save("PUBG", "Battle-royal / Multi-joueur");
			save("Rust", "Survie / Multi-joueur / Action / Open-World");
			save("Scum", "Survie / Multi-joueur / Action / Aventure / Open-World");
			save("Space Engineers", "Multi-joueur / Survie / Aventure / Open-World");
			save("Grand Theft Auto V", "Action / Aventure / Multi-joueur / Open-World");
			save("Red Dead Redemption 2", "Action / Aventure / Multi-joueur / Western / Open-World");
			save("Age of Empires III", "Gestion / strategie / Multi-joueur");
			save("Football Manager 2021", "Gestion / strategie / Football");
			save("Crusader Kings III", "Strategie / Simulation / Aventure / Multi-joueur");
			save("Planet Zoo", "Simulation / Gestion");
			save("Sea of Thieves", "Action / Pirate / Aventure / Multi-joueur / Open-World");
			save("Apex Legends", "Battle-royal / Multi-joueur");
			save("Cyberpunk 2077", "Aventure / Action / Open-World");
			save("Destiny 2", "Aventure / Action / Multi-joueur");
		save("Tom Clancy's Rainbow Six Siege", "Aventure / Action");
			save("The Elder Scrolls Online", "MMO / RPG / Action / Aventure");
			save("Dead by Daylight", "Coop / Action / Horreur");
			save("Total War: WARHAMMER II", "Exploration / Aventure / Action");
			save("The Forest", "Survie / Multi-joueur / Coop / Horreur / Open-World");
			save("ARK: Survival Evolved", "Survie / Multi-joueur / Action / Open-World");
			save("Final Fantasy XIV", "MMO / RPG / Fantaisie");
			save("Dota 2", "Fantaisie / Action / Multi-joueur");
			save("Arma 3", "Simulation / Militaire / Multi-joueur / Open-World");
			save("Elite Dangerous", "Simulation / Stratégie / Multijoueur");
			save("Skyrim", "RPG / Aventure / Action / Open-World");
			save("Warhammer: Vermintide 2", "Action / Coop");
			save("Conan Exiles", "Action / Aventure / Survie / Multi-joueur");
			save("The Witcher 3: Wild Hunt", "Action / Fantaisie / Open-World");
			save("Among Us", "Multi-joueur / Jeu de rôle");
			save("Euro Truck Simulator 2", "Simulation / Open-World");
			save("No Man's Sky",  "Exploration / Multi-joueur / Open-World");
			save("New World", "MMO / RPG / Aventure / Fantaisie");
			save("Squad", "Multi-joueur / Simulation / FPS");
			save("Cities: Skylines", "Simulation / Strategie");
			save("Farming Simulator 19", "Simulation / Open-World");
		}
	}

	private void save(String title,  String genre) {
		GameEntity game1 = new GameEntity();
		game1.setTitre(title);
		game1.setGenre(genre);
		repository.save(game1);
	}
}
