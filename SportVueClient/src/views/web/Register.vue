<template>
  <v-card>
    <v-card-title>
      <span class="headline">Register</span>
    </v-card-title>
    <v-card-text>
      <v-container>
        <form>
          <v-text-field
            :rules="nameRules"
            v-model="username"
            :counter="21"
            label="User name"
            required
          ></v-text-field>
          <v-text-field
            :rules="emailRules"
            v-model="email"
            label="E-mail"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            label="Password"
            required
            type="password"
          ></v-text-field>

          <v-btn class="mr-4" @click="register">Submit</v-btn>
        </form>
      </v-container>
    </v-card-text>
  </v-card>
</template>
<script>
export default {
  props: {
    closeRegisterDialog: {
      type: Function,
    },
  },
  data() {
    return {
      email: "",
      username: "",
      password: "",
      nameRules: [
        (v) => !!v || "Username is required",
        (v) => (v && v.length <= 21) || "Name must be less than 21 characters",
      ],
      emailRules: [
        (v) => !!v || "E-mail is required", // not exsits
        (v) => /.+@.+/.test(v) || "E-mail must be valid",
      ],
    };
  },
  watch: {
    username() {
      this.nameRules = [
        (v) => !!v || "Username is required",
        (v) => (v && v.length <= 21) || "Name must be less than 21 characters",
      ];
    },
    email() {
      this.emailRules = [
        (v) => !!v || "E-mail is required", // not exsits
        (v) => /.+@.+/.test(v) || "E-mail must be valid",
      ];
    },
  },
  methods: {
    register: function () {
      let self = this;

      let user = {
        username: this.username,
        email: this.email,
        password: this.password,
      };

      this.$store
        .dispatch("auth/register", user)
        .then((res) => {
          if (res.data.payload === 1) {
            self.nameRules = [(v) => !self.username || res.data.message];
          } else if (res.data.payload === 0) {
            self.emailRules = [(v) => !self.email || res.data.message];
          } else {
            self.closeRegisterDialog();
            self.email = "";
            self.username = "";
            self.password = "";
          }
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>
