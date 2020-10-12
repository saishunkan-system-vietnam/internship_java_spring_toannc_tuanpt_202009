<template>
  <v-card>
    <v-container fluid>
      <h1>Edit Team</h1>
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-row align="center">
          <v-col class="d-flex" cols="12" sm="6">
            <v-text-field
              v-model="name"
              :counter="21"
              :rules="nameRules"
              label="Team name"
              required
            ></v-text-field>
          </v-col>
          <v-col class="d-flex" cols="12" sm="6">
            <v-select
              :rules="[(v) => !!v || 'Item is required']"
              :items="type"
              v-model="selectedType"
              label="Type"
              dense
            ></v-select>
          </v-col>
          <!-- 
          <v-col class="d-flex" cols="12" sm="4">
            <v-file-input
              v-model="fileImage"
              :rules="imgRules"
              label="Change Logo"
            ></v-file-input>
          </v-col> -->
        </v-row>

        <!-- <v-row>
        <v-col cols="10">
          <v-select
            v-model="select"
            :items="members"
            item-text="name"
            item-value="id"
            :counter="50"
            label="Select Members"
            multiple
          >
            <template v-slot:selection="data">
              <v-chip
                :key="JSON.stringify(data.item)"
                v-bind="data.attrs"
                :input-value="data.selected"
                :disabled="data.disabled"
                @click:close="data.parent.selectItem(data.item)"
              >
                <v-avatar
                  class="accent white--text"
                  left
                  v-text="data.item.name.slice(0, 1).toUpperCase()"
                ></v-avatar>
                {{ data.item.name }}
              </v-chip>
            </template>
          </v-select>
        </v-col>

        <v-col cols="2">
          <ListMember :passSelectedType="selectedType" />
        </v-col>
      </v-row> -->

        <v-textarea
          clearable
          v-model="description"
          clear-icon="mdi-close-circle"
          label="Description"
          value="Something about team"
        ></v-textarea>

        <v-card-actions>
          <v-btn color="primary" x-large text @click="openEditTeam">
            Close
          </v-btn>
          <v-spacer> </v-spacer>
          <v-btn
            color="primary"
            x-large
            text
            @click.prevent="onSubmit(teamProps.idTeam)"
            v-if="changeButton"
            >Update</v-btn
          >
          <v-btn disabled v-else>Processing</v-btn>
        </v-card-actions>
      </v-form>
    </v-container>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  props: {
    openEditTeam: {
      type: Function,
    },
    teamProps: { type: Object },
  },
  data() {
    return {
      changeButton: true,
      valid: true,
      name: this.teamProps.nameTeam,
      type: ["Football", "TableTennis", "Baseball", "Basketball"],
      selectedType: this.teamProps.type,
      logo: "",
      description: this.teamProps.description,
      nameRules: [
        (v) => !!v || "Name is required",
        (v) => (v && v.length <= 21) || "Name must be less than 21 characters",
      ],
      select: [],
      items: [],
      members: [],
      fileImage: {},
      imgRules: [(v) => !!v || "Img is required"],
    };
  },
  mounted() {
    // console.log(this.teamProps);
  },
  watch: {
    selectedType() {
      // important name function need to equal name state
      this.members = this.items.filter((item) => {
        return item.type === this.selectedType && item.idTeam != 0;
      });
      // console.log(this.members);
    },
  },
  methods: {
    onSubmit(id) {
      let self = this;
      //   console.log("submit");
      //   console.log(this.fileImage);
      var teamForm = new FormData();
      teamForm.append("nameTeam", this.name);
      teamForm.append("type", this.selectedType);
      teamForm.append("description", this.description);
      // teamForm.append("file", this.fileImage);
      //   for (var value of teamForm.values()) {
      //     console.log(value);
      //   }
      axios
        .post(`http://localhost:8090/api/v1/team/updateInfo/${id}`, teamForm)
        .then((res) => {
          self.openEditTeam();
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>