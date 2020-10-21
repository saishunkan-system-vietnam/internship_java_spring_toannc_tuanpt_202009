<template>
  <v-container fluid>
    <h1>Create Team</h1>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-row align="center">
        <v-col class="d-flex" cols="12" sm="4">
          <v-text-field
            v-model="name"
            :counter="21"
            :rules="nameRules"
            label="Team Name"
            required
          ></v-text-field>
        </v-col>
        <v-col class="d-flex" cols="12" sm="4">
          <v-select
            :rules="[(v) => !!v || 'Logo is required']"
            :items="type"
            v-model="selectedType"
            label="Sport Type"
            dense
          ></v-select>
        </v-col>

        <v-col class="d-flex" cols="12" sm="4">
          <v-file-input
            accept="image/png, image/jpeg, image/bmp"
            :rules="rulesImage"
            v-model="fileImage"
            label="Add Logo"
          ></v-file-input>
        </v-col>
      </v-row>
      <v-textarea
        clearable
        v-model="description"
        clear-icon="mdi-close-circle"
        label="Description"
        value="Something about team"
      ></v-textarea>
      <v-btn
        :disabled="successDialog"
        :loading="successDialog"
        depressed
        color="primary"
        @click="onSubmit"
      >
        Create
      </v-btn>
    </v-form>

    <template>
      <div class="text-center">
        <v-dialog v-model="successDialog" hide-overlay persistent width="300">
          <v-alert class="mb-0" type="success"> Create Team Success! </v-alert>
        </v-dialog>
      </div>
    </template>
  </v-container>
</template>



<script>
import axios from "axios";
import ListMember from "@/views/admin/members/ListMember";

export default {
  components: { ListMember },
  data() {
    return {
      successDialog: false,
      idTeam: 0,
      valid: true,
      rulesImage:[],
      name: "",
      type: ["Football", "TableTennis", "Basketball"],
      selectedType: "",
      logo: "",
      description: "",
      nameRules: [
        (v) => !!v || "Name is required",
        (v) => (v && v.length <= 21) || "Name must be less than 21 characters",
      ],
      select: [],
      items: [],
      members: [],
      fileImage: {},
    };
  },
  mounted() {
    let self = this;
    axios
      .get("http://localhost:8090/api/v1/profiles/members")
      .then(function (response) {
        self.items = response.data;
      })
      .catch(function (error) {
        console.log(error);
      });
  },
  watch: {
    selectedType() {
      // important name function need to equal name state
      this.members = this.items.filter((item) => {
        return item.type === this.selectedType && item.idTeam != 0;
      });
      console.log(this.fileImage)
    },

    name() {
      this.nameRules = [
        (v) => !!v || "Name is required",
        (v) => (v && v.length <= 21) || "Name must be less than 21 characters",
      ];
    },

    successDialog(val) {
      if (!val) return;

      setTimeout(() => (this.successDialog = false), 100000);
    },
  },
  methods: {
    onSubmit() {
      console.log(!!this.fileImage.name)
      if(!!this.fileImage.name==false){
        this.rulesImage=[    (v)=>!!v.name||"Image is required"]
      }
      if(this.$refs.form.validate()==true){
      // console.log("submit");
      console.log(this.fileImage);
      
      var teamForm = new FormData();
      teamForm.append("nameTeam", this.name);
      teamForm.append("type", this.selectedType);
      teamForm.append("description", this.description);
      teamForm.append("file", this.fileImage);
      // for (var value of teamForm.values()) {
      //   console.log(value);
      // }
      let self = this;
      this.$store
        .dispatch("team/createTeam", teamForm)
        .then(function (response) {
          if (response.data.code === 9999 && response.data.payload != null) {
            self.nameRules = [(v) => !self.name || "Name has already exists"];
          } else if (
            response.data.code === 9999 &&
            response.data.payload === null
          ) {
            alert("Created Falied");
          } else {
            self.successDialog = !self.successDialog;
            setTimeout(function () {
              self.successDialog = !self.successDialog;
              self.$router.push("/LayoutTeam");
            }, 1500);
          }
        })
        .catch(function (error) {});
      }
      else{
        this.$refs.form.validate();
      }
    },
    
  },
};
</script>