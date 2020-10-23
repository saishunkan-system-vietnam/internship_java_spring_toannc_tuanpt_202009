<template>
  <div>
    <v-breadcrumbs :items="teamLink" large>
      <template v-slot:divider>
        <v-icon>mdi-chevron-right</v-icon>
      </template>
    </v-breadcrumbs>
    <v-card class="mx-auto my-12 container">
      <v-img height="300" :src="team.logo"></v-img>
      <v-row>
        <v-card-title
          ><h1>{{ team.nameTeam }}</h1></v-card-title
        >
        <v-spacer></v-spacer>
        <v-card-title
          ><h1>Win: {{ team.totalwin }}</h1></v-card-title
        >
      </v-row>
      <v-card-text>
        <v-row>
          <h3>{{ team.type }}</h3>

          <v-spacer> </v-spacer>
          <h3>TotalMatch: {{ team.totalmatch }}</h3>
        </v-row>
      </v-card-text>
      <v-card-text>
        <v-row>
          <div class="my-4 subtitle-1">
            {{ team.description }}
          </div>
          <v-spacer></v-spacer>
          <v-btn
            v-if="team.idTour == 0"
            color="primary"
            dark
            class="ma-2"
            @click="openEditTeam"
          >
            Edit Team
          </v-btn>
        </v-row>
      </v-card-text>
      <v-divider class="mx-4"></v-divider>

      <v-row>
        <v-col cols="12" md="5">
          <v-card-title><h2>List Member</h2></v-card-title>
        </v-col>
        <v-spacer></v-spacer>
        <v-col cols="12" md="2"> </v-col>
      </v-row>

      <v-card-text>
        <v-data-table
          :headers="headers"
          :items="desserts"
          class="elevation-1"
          :search="search"
        >
          <template v-slot:top>
            <v-toolbar flat color="white">
              <ListMember
                :passSelectedType="team.type"
                :memberProp="memberProp"
                :addedMember="addedMember"
                :updateTeam="updateTeam"
                :idTeam="parseInt($route.params.id)"
                :teamProps = "team"
              />
              <v-divider class="mx-4" inset vertical></v-divider>
              <v-spacer></v-spacer>
              <v-row class="mt-4">
                <v-col cols="12" sm="6" md="6">
                  <v-text-field
                    v-model="nameMemberSearch"
                    append-icon="mdi-magnify"
                    label="Name search"
                    single-line
                    hide-details
                  ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="6">
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
          <template v-slot:[`item.avatar`]="{ item }">
            <b-img
              :src="item.avatar"
              alt=""
              class="fixImg1"
              style="margin: 5px 0 5px 0"
            />
          </template>


          <template  v-slot:[`item.idTeam`]="{ item }">

            <v-btn v-if="team.idTour == 0" @click="removeMember(item)" small>Remove</v-btn>
          </template>
        </v-data-table>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn v-if="team.idTour == 0" color="primary" dark class="ma-2" @click="dialogConfirm = true">
          Confirm List
        </v-btn>
      </v-card-actions>

      <v-dialog v-model="dialogEditTeam" max-width="60%">
        <EditTeam
          :openEditTeam="openEditTeam"
          :teamProps="team"
          :desserts="desserts"
          :loadTeamById="loadTeamById"
        />
      </v-dialog>

      <v-dialog v-model="dialogConfirm" max-width="350">
        <v-card class="container" v-if="!success">
          <v-card-title class="headline">
            Confirm Apply This List ?
          </v-card-title>
          <v-card-actions>
            <v-btn color="green darken-1" text @click="dialogConfirm = false">
              Disagree
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn
              color="green darken-1"
              text
              @click="confirmUpdate($route.params.id)"
            >
              Agree
            </v-btn>
          </v-card-actions>
        </v-card>
        <template v-else>
          <v-alert class="mb-0" type="success"> Confirm List Success! </v-alert>
        </template>
      </v-dialog>
    </v-card>
  </div>
</template>

<script>
import EditTeam from "@/views/admin/team/EditTeam";
import ListMember from "@/views/admin/members/ListMember";

export default {
  components: { EditTeam, ListMember },

  data() {
    return {
      success: false,
      dialogConfirm: false,
      dialogEditTeam: false,
      checkAdd: true,
      search: "",
      nameMemberSearch: "",
      ageSearch: "",
      headers: [
        {
          text: "Avatar",
          align: "start",
          sortable: false,
          value: "avatar",
        },
        { text: "Name", value: "name", filter: this.nameMemberFilter },
        { text: "Email", value: "email" },
        { text: "Phone", value: "phone" },
        { text: "Age", value: "age", filter: this.ageFilter },
        { text: "Gender", value: "gender" },
        {
          text: "Actions",
          value: "idTeam",
          filter: (value, search, item) => {
            if (item.idTeam != 0) return true;
            return value;
          },
        },
      ],
      desserts: [],
      team: {},
      memberProp: {},
      teamDetail: {},
      teamLink: [
        {
          text: "Dashboard",
          disabled: false,
          href: "/admin/home",
        },
        {
          text: "Teams",
          disabled: false,
          href: "/LayoutTeam",
        },
      ],
    };
  },
  mounted() {
    this.loadListMember(this.$route.params.id);
    this.loadTeamById(this.$route.params.id);
  },
  methods: {
    isOpenModalMember: function () {
      this.dialogCreateMember = !this.dialogCreateMember;
      this.loadListMember();
    },

    loadTeamById(id) {
      let self = this;
      this.$store.dispatch("team/findTeamAndMembers", id).then((response) => {
        self.team = response.data;
        this.teamLink.push({
          text: response.data.nameTeam,
          disabled: false,
        });
      });
    },

    loadListMember(id) {
      let self = this;
      this.$store.dispatch("team/findTeamAndMembers", id).then((response) => {
        self.teamDetail = response.data;
        self.desserts = self.teamDetail.profile;
      });
    },

    updateTeam(id) {
      let self = this;
      this.teamDetail.profile = this.desserts;
      // console.log(this.teamDetail)
      this.$store
        .dispatch("team/updateMembersInTeam", this.teamDetail)
        .then(function (response) {
          self.loadListMember(id);
        })
        .catch(function (error) {});
    },

    removeMember(member) {
      let data = this.desserts.map((element, index) => {
        if (element.id === member.id) {
          element.idTeam = 0;
          this.memberProp = element;
          this.desserts.splice(index, 1);
        }
        return element;
      });
    },

    addedMember(member) {
      member.idTeam = this.$route.params.id;
      this.desserts.push(member);
    },

    openEditTeam: function () {
      this.dialogEditTeam = !this.dialogEditTeam;
    },

    confirmUpdate: function (id) {
      let self = this;
      this.success = !this.success;
      setTimeout(function () {
        self.success = !self.success;
        self.dialogConfirm = !self.dialogConfirm;
        self.updateTeam(id);
      }, 1500);
    },

    nameMemberFilter(value) {
      // If this filter has no value we just skip the entire filter.
      if (!this.nameMemberSearch) {
        return true;
      }

      return value.toLowerCase().includes(this.nameMemberSearch.toLowerCase());
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
<style >
.fixImg1 {
  width: 100px;
  height: 100px;
}
</style>>