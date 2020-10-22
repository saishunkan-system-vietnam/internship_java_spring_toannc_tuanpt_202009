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
              v-model="gender"
              :items="defaultGender"
              :rules="[(v) => !!v || 'Gender is required']"
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
          <v-col cols="12" md="3">
            <v-file-input
              accept="image/png, image/jpeg, image/bmp"
              :rules="rulesImage"
              v-model="fileImage"
              label="Add Avatar"
            ></v-file-input>
          </v-col>
        </v-row>

        <v-text-field v-model="address" label="Address" required></v-text-field>
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
    <template>
      <div class="text-center">
        <v-dialog v-model="successDialog" hide-overlay persistent width="300">
          <v-alert class="mb-0" type="success">
            Create Member Success!
          </v-alert>
        </v-dialog>
      </div>
    </template>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  props: {
    passSelectedType: String,
    isOpenModalMember: {
      type: Function,
    },
    loadMemberAfterCreate: {
      type: Function,
    },
  },

  data() {
    return {
      successDialog: false,
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
        (v) => (v && v.length <= 10) || "Name must be less than 21 characters",
      ],
      email: "",
      emailRules: [
        (v) => !!v || "E-mail is required", // not exsits
        (v) => /.+@.+/.test(v) || "E-mail must be valid",
      ],
      phone: "",
      phoneRules: [
        (v) => !!v || "Phone number is required",
        (v) =>
          (v && v.length <= 15) ||
          "Phone number must be less than 15 characters",
      ],
      age: "",
      ageRules: [
        (v) => !!v || "Age number is required",
        (v) =>
          (v <= 60 && v >= 6) || "Age must be less than 60 and greater than 6",
      ],
      gender: "",
      defaultGender: ["Male", "Female", "Orther"],
      rulesImage: [],
    };
  },
  mounted() {},
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
      // console.log(this.fileImage);
      if (!!this.fileImage.name == false) {
        this.rulesImage = [(v) => !!v.name || "Image is required"];
      }
      if (this.$refs.form.validate() == true) {
        let self = this;
        var memberForm = new FormData();
        memberForm.append("name", this.name);
        memberForm.append("email", this.email);
        memberForm.append("phone", this.phone);
        memberForm.append("age", this.age);
        memberForm.append("gender", this.gender);
        memberForm.append("address", this.address);
        memberForm.append("type", this.passSelectedType);
        memberForm.append("file", this.fileImage);
        //   for (var value of memberForm.values()) {
        //     console.log(value);
        //   }
        axios
          .post(
            "http://localhost:8090/api/v1/profiles/createMember",
            memberForm
          )
          .then((res) => {
            self.changeButton = !self.changeButton;
            if (res.data.code === 9999) {
              self.emailRules = [
                (v) => !self.email || "Email has already exists",
              ];
            } else {
              self.isOpenModalMember();
              self.successDialog = !self.successDialog;
              setTimeout(function () {
                self.successDialog = !self.successDialog;
                self.loadMemberAfterCreate(res.data.payload);
              }, 1100);
            }
          })
          .catch((e) => {
            self.changeButton = !self.changeButton;
          });
        self.changeButton = !self.changeButton;
        self.reset();
      } else {
        this.$refs.form.validate();
      }
    },
    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>