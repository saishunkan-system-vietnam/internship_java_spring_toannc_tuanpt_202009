<template>
  <div>
    <v-row justify="center">
      <v-btn color="primary" class="ma-2" dark @click="openListModalMember">
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
              <v-btn dark text @click="confirmList"> Save </v-btn>
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

                  <v-divider class="mx-4" inset vertical></v-divider>
                  <v-spacer></v-spacer>

                  <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
                  ></v-text-field>
                </v-toolbar>
              </template>
              <template v-slot:[`item.idTeam`]="{ item }">
                <p v-if="item.idTeam != 0" class="text1">
                  {{ item.team.nameTeam }}
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

      <v-dialog v-model="dialogCreateMember" max-width="60%">
        <CreateMember
          :isOpenModalMember="isOpenModalMember"
          :loadMemberAfterCreate="loadMemberAfterCreate"
          :passSelectedType = "passSelectedType"
        />
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
    idTeam: Number
  },
  data() {
    return {
      checkAdd: true,
      dialogMemberTable: false,
      dialogCreateMember: false,
      search: "",
      headers: [
        {
          text: "Avatar",
          align: "start",
          sortable: false,
          value: "avatar",
        },
        { text: "Name", value: "name" },
        { text: "Email", value: "email" },
        { text: "Phone", value: "phone" },
        { text: "Age", value: "age" },
        { text: "Gender", value: "gender" },
        { text: "Current Team", value: "idTeam" },
        { text: "Actions", value: "actions", sortable: false },
      ],
      team: {},
      desserts: [],
      members: [],
    };
  },
  computed:{
   
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
      // console.log(this.desserts);
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
      this.addedMember(member);
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
      this.desserts.push(member);
    },
    confirmList() {
      this.dialogMemberTable = !this.dialogMemberTable;
      this.updateTeam(this.idTeam);
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