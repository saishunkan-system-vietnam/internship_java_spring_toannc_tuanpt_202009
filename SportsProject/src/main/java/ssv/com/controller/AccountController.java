package ssv.com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ssv.com.RandomPass;
import ssv.com.dto.JwtResponse;
import ssv.com.dto.SearchAccountDto;
import ssv.com.entity.Account;
import ssv.com.service.AccountService;
import ssv.com.service.JwtService;

@RestController
@RequestMapping("/api/v1/account")
@ResponseBody
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private JwtService jwtService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(HttpServletRequest request, @RequestBody Account user) {
		String result = "";
		HttpStatus httpStatus = null;
		JwtResponse jwtResponse = new JwtResponse();
		if (!user.getUsername().isEmpty() || !user.getPassword().isEmpty()) {
			try {
				if (accountService.checkLogin(user)) {

					result = jwtService.generateTokenLogin(user.getUsername());
					jwtResponse.setAccount(accountService.loadUserByUsername(user.getUsername()));
					jwtResponse.setToken(result);
					httpStatus = HttpStatus.OK;
					return new ResponseEntity<JwtResponse>(jwtResponse, httpStatus);
				} else {
					result = "Sai thông tin tài khoản";
					httpStatus = HttpStatus.BAD_REQUEST;
					return new ResponseEntity<String>(result, httpStatus);
				}
			} catch (Exception ex) {
				result = "Lỗi hệ thống";
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity<JwtResponse>(jwtResponse, httpStatus);
		}
		return new ResponseEntity<String>("nhap sai du lieu ", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<String> sigup(@RequestBody Account acount) {
		String emailPattern = "\\w+@\\w+[.]\\w+";
		if (!acount.getUsername().isEmpty() || !acount.getPassword().isEmpty()
				|| !acount.getEmail().matches(emailPattern)) {
			if (accountService.checkEmail(acount.getEmail()) && !accountService.checkUser(acount)) {
				String hash = BCrypt.hashpw(acount.getPassword(), BCrypt.gensalt(12));
				acount.setPassword(hash);
				acount.setRole("ROLE_USER");
				accountService.add(acount);
				return new ResponseEntity<String>("Create!", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Tài khoản đã tồn tại hoặc email đã tồn tại!", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Nhap sai du lieu", HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/forget/{email}", method = RequestMethod.POST)
	public ResponseEntity<String> forget(@PathVariable String email) {
		String emailPattern = "\\w+@\\w+[.]\\w+";
		if (email.isEmpty() || email.matches(emailPattern)) {
			return new ResponseEntity<String>("nhap sai du lieu", HttpStatus.OK);
		}
		String result = "";
		HttpStatus httpStatus = null;
		try {
			if (!accountService.checkEmail(email)) {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(email);
				message.setSubject("Password");
				Account account = new Account();
				account.setEmail(email);
				account.setPassword(new RandomPass().randomAlphaNumeric(8));
				accountService.replacePass(account);
				message.setText(account.getPassword());
				result = "Email Sent!";
				this.emailSender.send(message);
				httpStatus = HttpStatus.OK;
			} else {
				result = "wrong";
				httpStatus = HttpStatus.BAD_REQUEST;
			}

		} catch (Exception e) {
			result = "fail";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<String>(result, httpStatus);

	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		if (accountService.checkid(id) && id != 1) {
			accountService.delete(id);
		} else if (id == 1) {
			return new ResponseEntity<String>("tk mac dinh khong the xoa", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("khong ton tai", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Delete", HttpStatus.OK);

	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<Account>> accountAll() {
		List<Account> list = new ArrayList<Account>();
		list = accountService.findAll();
		return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/getById/{id}")
	public ResponseEntity<Account> findById(@PathVariable(value = "id") int id) {
		return new ResponseEntity<Account>(accountService.findById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<SearchAccountDto> search(@RequestParam int page, @RequestParam int pageSize,
			@RequestParam String nameSearch, @RequestParam String type) {
		if (nameSearch == "" && type == "") {
			nameSearch = "";
			type = "id";
		}
		;
		return new ResponseEntity<SearchAccountDto>(accountService.search(page, pageSize, nameSearch, type),
				HttpStatus.OK);
	}

}
