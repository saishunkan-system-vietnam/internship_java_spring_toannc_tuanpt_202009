<template>
  <v-card>
    <v-container fluid>
      <h1>Edit Team</h1>
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-row align="center">
          <v-col class="d-flex" cols="12" sm="4">
            <v-text-field
              v-model="name"
              :counter="21"
              :rules="nameRules"
              label="Team name"
              required
            ></v-text-field>
          </v-col>
          <v-col class="d-flex" cols="12" sm="4">
            <v-select
              :rules="[(v) => !!v || 'Item is required']"
              :items="type"
              v-model="selectedType"
              label="Type"
              dense
            ></v-select>
          </v-col>
          <v-col class="d-flex" cols="12" sm="4">
            <v-file-input
              v-model="fileImage"
              label="Change Logo"
              accept="image/png, image/jpeg, image/bmp"
              :rules="rulesImage"
            ></v-file-input>
          </v-col>
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
            @click="onSubmit(teamProps.idTeam)"
            >Update</v-btn
          >
        </v-card-actions>
      </v-form>
    </v-container>

    <v-dialog persistent v-model="dialogSuccess" max-width="500">
      <template>
        <v-alert class="mb-0" type="success"> Update Success! </v-alert>
      </template>
    </v-dialog>
  </v-card>
</template>

<script>

export default {
  props: {
    openEditTeam: {
      type: Function,
    },
    teamProps: { type: Object },
    removeMember: {
      type: Function,
    },
    loadTeamById: {
      type: Function,
    },
  },
  data() {
    return {
      checkLogo: false,
      dialogSuccess: false,
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
      fileImage: [],
      rulesImage: [
        (v) => {
          if (v == undefined || Array.isArray(v)) {
            return false || "Item is required";
          }
          return true;
        },
      ],
    };
  },
  mounted() {},
  watch: {
    selectedType() {
      // important name function need to equal name state
      this.members = this.items.filter((item) => {
        return item.type === this.selectedType && item.idTeam != 0;
      });
    },
  },
  watch: {
    fileImage() {
      if (this.fileImage == undefined) {
        this.fileImage = [];
        this.rulesImage = [
          (v) => {
            if (v == undefined || Array.isArray(v)) {
              return false || "Item is required";
            }
            return true;
          },
        ];
      }
      if (!!this.fileImage.name) {
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
    onSubmit(id) {
      if (!this.$refs.form.validate()) {
        this.$refs.form.validate();
        this.busy = false;
      } else {
        let self = this;
        var teamForm = new FormData();
        teamForm.append("nameTeam", this.name);
        teamForm.append("type", this.selectedType);
        teamForm.append("description", this.description);
        teamForm.append("file", this.fileImage);
        if (teamForm.get("type") != self.teamProps.type) {
          self.teamProps.profile.forEach((member) => {
            member.idTeam = 0;
            self.teamProps.profile.splice(member);
          });
          this.removeMembers(self.teamProps);
        }
        console.log(teamForm);
        this.$store
          .dispatch("team/updateTeam", {
            id: id,
            formRequest: teamForm,
          })
          .then((res) => {
            self.dialogSuccess = !self.dialogSuccess;
            self.openEditTeam();
            setTimeout(function () {
              self.loadTeamById(self.teamProps.idTeam);
              self.dialogSuccess = !self.dialogSuccess;
            }, 1500);
          })
          .catch((e) => {
            // console.log(e);
          });
      }
    },

    removeMembers(team) {
      let self = this;
      this.$store.commit("auth/auth_overlay");
      this.$store
        .dispatch("team/updateMembersInTeam", team)
        .then(function (response) {
          window.location.reload();
          self.$store.commit("auth/auth_overlay");
        })
        .catch(function (error) {});
    },

    checkLogoFunction() {
      this.checkLogo = !this.checkLogo;
    },
  },
};
</script>