package com.ridgue.jangular;

import com.ridgue.jangular.database.entity.Photo;
import com.ridgue.jangular.database.entity.User;
import com.ridgue.jangular.database.repository.PhotoRepository;
import com.ridgue.jangular.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JangularApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JangularApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhotoRepository photoRepository;

	@Override
	public void run(String...args) throws Exception {
		this.userRepository.save(new User("gustavo", "senhadogu", "gustavo.sobrenome@gmail.com"));
		this.userRepository.save(new User("gabriel", "senhadobiel", "gabriel.sobrenome@gmail.com"));
		this.userRepository.save(new User("teste", "123", "teste@gmail.com"));
		this.userRepository.save(new User("t", "1", "teste@gmail.com"));
//
		this.photoRepository.save(new Photo("leao",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Sultan_the_Barbary_Lion.jpg/440px-Sultan_the_Barbary_Lion.jpg",
				userRepository.findByUsername("gustavo")));

		this.photoRepository.save(new Photo("maritaca",
				"https://i.ytimg.com/vi/jrIivkIhbaY/maxresdefault.jpg",
				userRepository.findByUsername("gabriel")));

		this.photoRepository.save(new Photo("periquito",
				"https://www.petz.com.br/blog/wp-content/uploads/2017/09/periquito-02-1280x720.jpg",
				userRepository.findByUsername("teste")));

		this.photoRepository.save(new Photo("canario do reino",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVN5K_liCFw2_WSGjpe8KrRlkY0ubtn561Ow&usqp=CAU",
				userRepository.findByUsername("gustavo")));

		this.photoRepository.save(new Photo("Ben Te Vi",
				"https://i.ytimg.com/vi/TYMAB7nKtOs/maxresdefault.jpg",
				userRepository.findByUsername("teste")));

		this.photoRepository.save(new Photo("passaro preto",
				"https://s3.amazonaws.com/media.wikiaves.com.br/images/0802/2080539_55ea38441c551772bcd6457104460e90.jpg",
				userRepository.findByUsername("gabriel")));

		this.photoRepository.save(new Photo("canario do reino femea",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRa9CEDkwrORYlPjOm-dYLGA63HOjF0AcM5Zw&usqp=CAU",
				userRepository.findByUsername("gabriel")));

		this.photoRepository.save(new Photo("peixe palhaco",
				"https://blog.pescagerais.com.br/wp-content/uploads/2021/02/peixe-palhaco-procurando-nemo.jpg",
				userRepository.findByUsername("gabriel")));


	}
}
