<template>
  <v-card>
    <v-card-title> Create Member </v-card-title>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-card-text>
        <v-row>
          <v-col cols="12" md="6">
            <v-text-field
              v-model="name"
              :rules="nameRules"
              :counter="21"
              label="Full Name"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="3">
            <v-text-field
              v-model="phone"
              label="PhoneNumber"
              :rules="phoneRules"
              :counter="15"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="3">
            <v-select
              :rules="[(v) => !!v || 'Item is required']"
              :items="type"
              label="Sport Type"
            ></v-select>
          </v-col>
          <v-col cols="12" md="3">
            <v-select
              v-model="gender"
              :items="defaultGender"
              label="Gender"
            ></v-select>
          </v-col>
          <v-col cols="12" md="3">
            <v-text-field
              v-model="age"
              label="Age"
              :rules="ageRules"
              :counter="2"
              required
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="9">
            <v-text-field
              v-model="address"
              label="Address"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="3">
            <v-file-input v-model="fileImage" label="Add Avatar"></v-file-input>
          </v-col>
        </v-row>
      </v-card-text>
      <v-card-actions>
        <v-spacer> </v-spacer>
        <v-btn
          color="primary"
          x-large
          text
          @click.prevent="onSubmit"
          v-if="changeButton"
          >Create</v-btn
        >
        <v-btn disabled v-else>Processing</v-btn>
      </v-card-actions>
    </v-form>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  props: {
    isOpenModalMember: {
      type: Function,
    },
    loadMemberAfterCreate: {
      type: Function,
    },
  },

  data() {
    return {
      changeButton: true,
      valid: false,
      response: "",
      dialogCreateMember: false,
      fileImage: {},
      address: "",
      name: "",
      number: 10,
      nameRules: [
        (v) => !!v || "Name is required",
        (v) => v.length <= 21 || "Name must be less than 21 characters",
      ],
      email: "",
      emailRules: [
        (v) => !!v || "E-mail is required", // not exsits
        (v) => /.+@.+/.test(v) || "E-mail must be valid",
      ],
      phone: "",
      phoneRules: [
        (v) => !!v || "Phone number is required",
        (v) => v.length <= 15 || "Phone number must be less than 15 characters",
      ],
      age: "",
      ageRules: [
        (v) => !!v || "Age number is required",
        (v) =>
          (v.length === 2 && v <= 60 && v >= 6) ||
          "Age must be less than 60 and greater than 6",
      ],
      gender: "",
      defaultGender: ["Male", "Female", "Orther"],
      type: ["Football", "TableTennis" , "Baseball", "Basketball"],
      selectedType: ''
    };
  },
  //   created() {
  //     console.log("sssacca");
  //   },
  //   mounted() {
  //     console.log("sss");
  //   },
  watch: {
    email() {
      this.emailRules = [
        (v) => !!v || "E-mail is required", // not exsits
        (v) => /.+@.+/.test(v) || "E-mail must be valid",
      ];
    },
  },
  methods: {
    onSubmit() {
      console.log("submit");
      let self = this;
      //   console.log(this.fileImage);
      var memberForm = new FormData();
      memberForm.append("name", this.name);
      memberForm.append("email", this.email);
      memberForm.append("phone", this.phone);
      memberForm.append("age", this.age);
      memberForm.append("gender", this.gender);
      memberForm.append("address", this.address);
      memberForm.append("file", this.fileImage);
      //   for (var value of memberForm.values()) {
      //     console.log(value);
      //   }
      axios
        .post("http://localhost:8090/api/v1/profiles/createMember", memberForm)
        .then((res) => {
          self.changeButton = !self.changeButton;
          // console.log(res.data);
          if (res.data.code === 9999) {
            self.emailRules = [(v) => !self.email || res.data];
          } else {
            self.isOpenModalMember();
            self.loadMemberAfterCreate(res.data.payload);
          }
        })
        .catch((e) => {
          self.changeButton = !self.changeButton;
          console.log(e);
        });

      self.changeButton = !self.changeButton;
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>