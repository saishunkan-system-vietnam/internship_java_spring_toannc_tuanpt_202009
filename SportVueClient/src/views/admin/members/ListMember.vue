<template>
  <div>
    <v-row justify="center">
      <v-btn v-if="teamProps.idTour == 0" color="primary" class="ma-2" dark @click="openListModalMember">
        Add Members
      </v-btn>

      <v-dialog
        v-model="dialogMemberTable"
        fullscreen
        hide-overlay
        transition="dialog-bottom-transition"
        scrollable
      >
        <v-card tile>
          <v-toolbar flat dark color="primary" class="space">
            <v-btn icon dark @click="dialogMemberTable = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title>Close</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn
                :disabled="successDialog"
                :loading="successDialog"
                dark
                text
                @click="confirmList"
              >
                Save
              </v-btn>
            </v-toolbar-items>
          </v-toolbar>
          <v-card-text>
            <v-divider></v-divider>
            <v-data-table
              :headers="headers"
              :items="desserts"
              class="elevation-1"
              :search="search"
            >
              <template v-slot:top>
                <v-toolbar flat color="white">
                  <v-btn
                    color="primary"
                    dark
                    class="ma-2"
                    @click="isOpenModalMember"
                  >
                    Create Member
                  </v-btn>

                  <v-divider class="mb-4" inset vertical></v-divider>
                  <v-spacer></v-spacer>

                  <v-row class="mt-4">
                    <v-col cols="12" sm="3" md="3">
                      <v-select
                        v-model="typeSearch"
                        :items="items"
                        label="Search gender"
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-text-field
                        v-model="nameMemberSearch"
                        append-icon="mdi-magnify"
                        label="Name search"
                        single-line
                        hide-details
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-text-field
                        v-model="emailSearch"
                        append-icon="mdi-magnify"
                        label="Email search"
                        single-line
                        hide-details
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="3" md="3">
                      <v-text-field
                        v-model="ageSearch"
                        append-icon="mdi-magnify"
                        label="Age"
                        single-line
                        hide-details
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-toolbar>
              </template>
              <template v-slot:[`item.idTeam`]="{ item }">
                <p v-if="item.idTeam != 0" class="text1">
                  {{ !!item.team ? item.team.nameTeam : "" }}
                </p>
                <p v-else class="text">Available</p>
              </template>

              <template v-slot:[`item.avatar`]="{ item }">
                <b-img
                  :src="item.avatar"
                  alt=""
                  style="margin: 5px 0 5px 0"
                  class="fixImg"
                />
              </template>

              <template v-slot:[`item.actions`]="{ item }">
                <template>
                  <v-btn small @click="addMember(item)"
                    >Add<v-icon small class="mr-2">add</v-icon></v-btn
                  >
                </template>
              </template>
            </v-data-table>
          </v-card-text>

          <div style="flex: 1 1 auto"></div>
        </v-card>
      </v-dialog>

      <v-dialog v-model="dialogCreateMember" max-width="68%">
        <CreateMember
          :isOpenModalMember="isOpenModalMember"
          :loadMemberAfterCreate="loadMemberAfterCreate"
          :passSelectedType="passSelectedType"
        />
      </v-dialog>

      <v-dialog  v-model="successDialog" hide-overlay persistent width="300">
        <v-alert class="mb-0" type="success">
          List Member Added Success!
        </v-alert>
      </v-dialog>
    </v-row>
  </div>
</template>
<script>
import axios from "axios";
import CreateMember from "@/views/admin/members/CreateMember.vue";

export default {
  components: {
    CreateMember,
  },
  props: {
    passSelectedType: String,
    memberProp: Object,
    addedMember: {
      type: Function,
    },
    updateTeam: {
      type: Function,
    },
    idTeam: Number,
    teamProps: Object,
    item: Object
  },
  data() {
    return {
      successDialog: false,
      checkAdd: true,
      dialogMemberTable: false,
      dialogCreateMember: false,
      search: "",
      typeSearch: null,
      items: ["Male", "Female", "Orther"],
      nameMemberSearch: "",
      emailSearch: "",
      ageSearch: "",
      headers: [
        {
          text: "Avatar",
          align: "start",
          sortable: false,
          value: "avatar",
        },
        { text: "Name", value: "name", filter: this.nameMemberFilter },
        { text: "Email", value: "email", filter: this.emailFilter },
        { text: "Phone", value: "phone" },
        { text: "Age", value: "age", filter: this.ageFilter },
        { text: "Gender", value: "gender", filter: this.genderFilter },
        { text: "Current Team", value: "idTeam" },
        { text: "Actions", value: "actions", sortable: false },
      ],
      team: {},
      desserts: [],
      members: [],
    };
  },
  watch: {
    memberProp: {
      immediate: true,
      deep: true,
      handler(newValue, oldValue) {
        this.desserts.push(newValue);
      },
    },
    passSelectedType: {
      //when props change then call api not declare in mounted anymore
      immediate: true,
      deep: true,
      handler(newValue, oldValue) {
        this.loadListMember();
      },
    },
  },
  methods: {
    isOpenModalMember: function () {
      this.dialogCreateMember = !this.dialogCreateMember;
    },

    openListModalMember: function () {
      this.dialogMemberTable = true;
    },

    loadListMember() {
      let self = this;
      axios
        .get("http://localhost:8090/api/v1/profiles/members")
        .then(function (response) {
          self.desserts = response.data.filter((item) => {
            return item.type === self.passSelectedType && item.idTeam == 0;
          });
        })
        .catch(function (error) {});
    },

    addMember(member) {
      this.checkAdd = false;
      let indexRemove = 0;
      let obj = Object.assign({}, member);
      this.addedMember(obj);
      this.desserts.forEach((element, index) => {
        if (element.id === member.id) {
          indexRemove = index;
        }
      });
      this.desserts.splice(indexRemove, 1);
    },

    removeAdd(member) {
      this.checkAdd = true;
    },

    loadMemberAfterCreate(member) {
      this.desserts.unshift(member); // unshift is add into 1st positions , push is add last positions
    },

    confirmList() {
      let self = this;
      this.successDialog = !this.successDialog;
      setTimeout(function () {
        self.successDialog = !self.successDialog;
        self.dialogMemberTable = !self.dialogMemberTable;
        self.updateTeam(self.idTeam);
      }, 1000);
    },

    genderFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.typeSearch) {
        return true;
      }

      return value === this.typeSearch;
    },

    nameMemberFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.nameMemberSearch) {
        return true;
      }

      return value.toLowerCase().includes(this.nameMemberSearch.toLowerCase());
    },

    emailFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.emailSearch) {
        return true;
      }
      return value.toLowerCase().includes(this.emailSearch.toLowerCase());
    },

    ageFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.ageSearch) {
        return true;
      }
      return value.toLowerCase().includes(this.ageSearch.toLowerCase());
    },
  },
};
</script>

<style>
.text1 {
  color: #0288d1;
  margin: 0 !important;
}
.text {
  color: #4caf50;
  margin: 0 !important;
}
.space {
  max-height: 64px !important;
}
.fixImg {
  height: 100px;
  width: 100px;
}
</style>