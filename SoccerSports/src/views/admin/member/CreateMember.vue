<template>
  <v-card>
    <v-card-title> Create Member </v-card-title>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-card-text>
        <v-row>
          <v-col cols="12" md="6">
            <v-text-field
              v-model.trim="name"
              :rules="nameRules"
              :counter="21"
              label="Full Name"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field
              v-model.trim="email"
              :rules="emailRules"
              label="E-mail"
              required
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="3">
            <v-text-field
              v-model.trim="phone"
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
              v-model="birthDate.mydate"
              label="Birth"
              :rules="birthDateRules"
              required
              type="date"
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
        <v-row>
          <v-col cols="12" md="6" xl="6">
            <v-text-field
              :rules="countryRules"
              v-model="country"
              label="Country"
              required
            ></v-text-field
          ></v-col>
          <v-col cols="12" md="6" xl="6">
            <v-select
              v-model="position"
              :items="defaultPosition"
              :rules="[(v) => !!v || 'Position is required']"
              label="Position"
            ></v-select> </v-col
        ></v-row>
      </v-card-text>
      <v-card-actions>
        <v-btn color="error" x-large text @click="isOpenModalMember">
          Cancel
        </v-btn>
        <v-btn color="error" x-large text @click="reset"> Reset Form </v-btn>
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
var vueData = {};
vueData.mydate = "1996-01-21";
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
      successDialog: false,
      changeButton: true,
      valid: false,
      response: "",
      dialogCreateMember: false,
      fileImage: [],
      country: "1",
      name: "1",
      countryRules: [(v) => !!v || "Country is required"],
      nameRules: [
        (v) => !!v || "Name is required",
        (v) => (v && v.trim().length != 0) || "Name is required",
      ],
      email: "1@gmail.com",
      emailRules: [
        (v) => !!v || "Email is required",
        (v) => {
          let inValid = /\s/;
          return !inValid.test(v) || "E-mail can not have white space";
        },
        (v) => !!/\S+@\S+\.\S+/.test(v) || "E-mail must be valid",
        (v) => (v && v.trim().length != 0) || "Email is required",
      ],
      phone: "112321332",
      phoneRules: [
        (v) => !!v || "Phone is required",
        (v) => {
          let inValid = /^[0-9]+$/;
          return inValid.test(v) || "Phone must be a number";
        },
        (v) => {
          return (
            (v != undefined && v.trim().length > 5) ||
            "Phone number have at least 6 digit"
          );
        },
        (v) => (v && v.trim().length != 0) || "Name is required",
      ],
      birthDate: vueData,
      birthDateRules: [
        (v) => !!v || "Age number is required",
        (v) => v.substring(0, v.indexOf("-")).length == 4 || "Unvalid Year",
        (v) =>
          (parseInt(v.substring(0, v.indexOf("-"))) > 1900 &&
            parseInt(v.substring(0, v.indexOf("-"))) < 3000) ||
          "Year select bettwen 1900 - 3000",
      ],
      gender: "",
      defaultGender: ["Male", "Female", "Orther"],
      position: "",
      defaultPosition: [
        "Goalkeepers",
        "Defenders",
        "Midfielders",
        "Forwards",
        "Coach",
      ],
      rulesImage: [],
    };
  },

  watch: {
    fileImage() {
      if (this.fileImage == undefined) {
        this.fileImage = new File([""], "");
        this.rulesImage = [
          (v) => {
            if (v == undefined || v.name == "") {
              return true;
            }
          },
        ];
      }
      let isContainImage = !this.fileImage.name;
      if (!isContainImage) {
        this.rulesImage = [
          (v) =>
            v.type == "image/png" ||
            v.type == "image/jpeg" ||
            v.type == "image/bmp" ||
            "Wrong type image",
        ];
      }
    },
  },

  methods: {
    onSubmit() {
      if (!this.$refs.form.validate()) {
        this.$refs.form.validate();
      } else {
        // console.log(this.fileImage);
        // console.log(this.birthDate);
        let self = this;
        var memberForm = new FormData();
        memberForm.append("name", this.name);
        memberForm.append("email", this.email);
        memberForm.append("phone", this.phone);
        memberForm.append("gender", this.gender);
        memberForm.append("age", this.birthDate.mydate);
        memberForm.append("country", this.country);
        memberForm.append("position", this.position);
        if (this.fileImage.size > 0) {
          memberForm.append("file", this.fileImage);
        }
        // for (var value of memberForm.values()) {
        //   console.log(value);
        // }
        this.$store
          .dispatch("member/createMember", memberForm)
          .then((response) => {
            let res = response.data;
            self.changeButton = !self.changeButton;
            if (res.code === 9999 && res.payload === 300) {
              alert(res.message);
            } else if (res.code === 9999 && res.payload === 301) {
              alert(res.message);
            } else if (res.code === 9999 && res.payload === 400) {
              alert("Create Failed");
            } else {
              self.isOpenModalMember();
              self.successDialog = !self.successDialog;
              setTimeout(function () {
                self.successDialog = !self.successDialog;
                self.loadMemberAfterCreate(res.payload);
              }, 1100);
              self.reset();
            }
          })
          .catch((e) => {
            alert(e);
            self.changeButton = !self.changeButton;
          });
        self.changeButton = !self.changeButton;
      }
    },

    reset() {
      this.$refs.form.reset();
    },
  },
};
</script>