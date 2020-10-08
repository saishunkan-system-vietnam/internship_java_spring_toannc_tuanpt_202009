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
            :rules="[(v) => !!v || 'Item is required']"
            :items="type"
            v-model="selectedType"
            label="Sport Type"
            dense
          ></v-select>
        </v-col>

        <v-col class="d-flex" cols="12" sm="4">
          <v-file-input
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
      <v-btn depressed color="primary" @click="onSubmit"> Create </v-btn>
    </v-form>
  </v-container>
</template>

<script>
import axios from "axios";
import ListMember from "@/views/admin/members/ListMember";

export default {
  components: { ListMember },
  data() {
    return {
      idTeam: 0,
      valid: true,
      name: "",
      type: ["Football", "TableTennis", "Baseball", "Basketball"],
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
      console.log(this.members);
    },
  },
  methods: {
    onSubmit() {
      console.log("submit");
      console.log(this.fileImage);
      var teamForm = new FormData();
      teamForm.append("nameTeam", this.name);
      teamForm.append("type", this.selectedType);
      teamForm.append("description", this.description);
      teamForm.append("file", this.fileImage);
        for (var value of teamForm.values()) {
          console.log(value);
        }
      axios
        .post("http://localhost:8090/api/v1/team/createTeam", teamForm)
        .then((res) => {
          alert(res.data);
          this.$router.push("/LayoutTeam");
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>