<template>
  <div>
    <b-container fluid>
      <div class="text-center">
        <h1>{{ data.nameTour }}</h1>
        <h3>Type of sports :{{ data.type }}</h3>
        {{ data.timeStart }}<b-icon-arrow-right></b-icon-arrow-right
        >{{ data.timeEnd }}<br />
      </div>
      <p v-if="data.status == 2">
        <img
          style="width: 50px"
          src="https://image.freepik.com/free-vector/trophy-vector-icon-illustration-with-laurel-wreath_38841-83.jpg"
        />{{ this.winner }}
      </p>

      <b-container fluid style="margin-top: 100px">
        <v-card>
          <v-card-title>
            Teams
            <v-spacer> </v-spacer>

            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-btn
            class="mx-2"
            fab
            dark
            color="indigo"
            @click.stop="dialog = true"
          >
            <v-icon dark> mdi-plus </v-icon>
          </v-btn>
          <v-data-table :headers="headers" :items="team" :search="search">
            <template v-slot:item="row">
              <tr>
                <td>{{ row.item.nameTeam }}</td>
                <td><b-avatar :src="row.item.logo" square></b-avatar></td>
                <td>{{ row.item.profile.length }}</td>
                <td>{{ row.item.description }}</td>
                <td>
                  <v-btn fab small @click="openMemberTable(row.item.idTeam)">
                    <v-icon>mdi-forward</v-icon>
                  </v-btn>

                  <v-btn
                    class="mx-2"
                    fab
                    small
                    @click="deleteTeam(row.item.idTeam)"
                  >
                    <v-icon dark>mdi-delete</v-icon>
                  </v-btn>
                </td>
              </tr>
            </template>
          </v-data-table>
        </v-card>
        <b-modal id="modal-1" centered title="Delete" @ok="handleOk()">
          <p class="my-4">Are you sure!</p>
        </b-modal>
      </b-container>

      <b-container fluid style="margin-top: 100px">
        <v-card>
          <v-card-title>
            {{ schedule.length }} :Schedule
            <v-spacer> </v-spacer>
            <v-text-field
              v-model="searchSchedule"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-btn
            class="mx-2"
            fab
            dark
            color="indigo"
            @click.stop="dialogSchedule = true"
          >
            <v-icon dark> mdi-plus </v-icon>
          </v-btn>
          <v-data-table
            :headers="headersSchedule"
            :items="schedule"
            :search="searchSchedule"
          >
            <template v-slot:item="row">
              <tr>
                <td>{{ row.item.title }}</td>
                <td>
                  {{ row.item.timeStart }}<v-icon>mdi-forward</v-icon
                  >{{ row.item.timeEnd }}
                </td>
                <td>
                  {{ row.item.team[0].nameTeam }}<v-icon>mdi-fencing </v-icon
                  >{{ row.item.team[1].nameTeam }}
                </td>
                <td>
                  {{ row.item.scoreTeam1 != 0 ? row.item.scoreTeam1 : "?" }}-{{
                    row.item.scoreTeam2 != 0 ? row.item.scoreTeam2 : "?"
                  }}
                </td>
                <td>
                  {{
                    row.item.status == 0
                      ? "not"
                      : row.item.status == 1
                      ? "doing"
                      : "end"
                  }}
                </td>
                <td>
                  <router-link
                    :to="{ path: '/DetailSchedule/' + row.item.idSchedule }"
                    id="${schedule.idSchedule}"
                  >
                    <v-btn fab small>
                      <v-icon>mdi-forward</v-icon>
                    </v-btn>
                  </router-link>
                  <v-btn
                    v-if="row.item.status == 0"
                    class="mx-2"
                    fab
                    small
                    @click="editSchedule(row.item)"
                  >
                    <v-icon dark>mdi-pencil</v-icon>
                  </v-btn>
                  <v-btn
                    class="mx-2"
                    fab
                    small
                    @click="deleteSchedule(row.item.idSchedule)"
                  >
                    <v-icon dark>mdi-delete</v-icon>
                  </v-btn>
                </td>
              </tr>
            </template>
          </v-data-table>
        </v-card>
        <b-modal id="modal-2" centered title="Delete" @ok="handleOkSchedule()">
          <p class="my-4">Are you sure!</p>
        </b-modal>
      </b-container>
    </b-container>
    <v-row justify="center">
      <v-dialog persistent v-model="dialog" max-width="290" scrollable>
        <v-card>
          <v-card-title class="headline"> Choose Team: </v-card-title>

          <v-card-text style="height: 300px">
            <v-radio-group
              v-model="teamAdd"
              column
              v-for="item in items"
              :key="item.idTeam"
            >
              <v-radio :label="item.nameTeam" :value="item.idTeam"></v-radio>
            </v-radio-group>
          </v-card-text>

          <v-card-actions>
            <v-btn @click="dialog = false">Cancel</v-btn>
            <v-btn @click="addTeam">Ok</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    <v-dialog v-model="dialogSchedule" max-width="1000px">
      <v-card>
        <v-card-title class="headline"> Add Schedule </v-card-title>
        <v-card-text>
          <AddSchedule
            :idTour="this.$route.params.id"
            :hideModal="hideModal"
            :getListSchedule="getTournamentSchedule"
            :getTournamentTeam="getTournamentTeam"
          ></AddSchedule>
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialogEditSchedule" max-width="1000px">
      <v-card>
        <v-card-title class="headline"> Edit </v-card-title>

        <v-card-text>
          <EditSchedule
            :hideModal="hideModal"
            :getData="getTournamentSchedule"
            :dataEdit="dataEdit"
          ></EditSchedule>
        </v-card-text>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialogMemberTable" max-width="1000px">
      <MemberTable :teamData="teamData" />
    </v-dialog>
  </div>
</template>
<script>
import AddSchedule from "./AddSchedule";
import EditSchedule from "../schedule/EditSchedule";

import MemberTable from "@/views/admin/tournament/MembersTable";
export default {
  components: {
    AddSchedule,
    MemberTable,
    EditSchedule,
  },
  data() {
    return {
      winner: "",
      teamIdProp: "",
      dataEdit: "",
      dialogEditSchedule: false,
      dialogMemberTable: false,
      dialogScheduleDelete: false,
      teamAdd: "",
      dialog: false,
      dialogSchedule: false,
      type: "",
      search: "",
      searchSchedule: "",
      data: {},
      team: [],
      schedule: [],
      items: [],
      idDelete: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: true,
          value: "nameTeam",
        },
        { text: "Logo", value: "logo", sortable: false },
        { text: "Total Member", value: "type" },
        { text: "Description", sortable: false },
        { text: "action", sortable: false },
      ],
      headersSchedule: [
        {
          text: "Title",
          align: "start",
          sortable: true,
          value: "title",
        },
        { text: "Time", value: "timeStart" },
        { text: "Team", value: "type", sortable: false },
        { text: "score", sortable: false },
        { text: "status", value: "status" },
        { text: "action", sortable: false },
      ],
      idTour: this.$route.params.id,
      teamData: {},
    };
  },

  async created() {
    let data1 = await this.getTournament();
    this.type = data1.type;
    this.getTournamentTeam();
    this.getTournamentSchedule();
    this.getTeamWait(data1.type);
    this.idTour = this.$route.params.id;
  },
  watch: {
    team() {
      if (this.type != "") {
        this.getTeamWait(this.type);
      }
    },
  },
  methods: {
    editSchedule(id) {
      this.dataEdit = id;
      this.dialogEditSchedule = true;
    },
    handleOk() {
      this.$store
        .dispatch("tournament/deleteTeam", this.idDelete)
        .then((response) => {
          alert(response.data);
          this.team.splice(this.index);
          this.getTournamentTeam();
        });
    },
    handleOkSchedule() {
      this.$store
        .dispatch("schedule/deleteSchedule", this.idDeleteSchedule)
        .then((response) => {
          alert(response.data);
          this.getTournamentSchedule();
          this.getTournamentTeam();
        });
    },
    deleteSchedule(id) {
      this.$bvModal.show("modal-2");
      this.index1 = this.team.indexOf(id);
      this.idDeleteSchedule = id;
    },
    deleteTeam(id) {
      this.$bvModal.show("modal-1");
      this.index = this.team.indexOf(id);
      this.idDelete = id;
    },
    getTournamentSchedule() {
      this.$store
        .dispatch("schedule/getByIdTour", this.$route.params.id)
        .then((response) => {
          this.schedule = response.data;
        });
    },
    hideModal() {
      this.dialogSchedule = false;
      this.dialogEditSchedule = false;
    },
    async getTournament() {
      let response = await this.$store.dispatch(
        "tournament/getById",
        this.$route.params.id
      );
      return response.data;
    },
    getTournamentTeam() {
      this.$store
        .dispatch("tournament/getById", this.$route.params.id)
        .then((response) => {
          this.data = response.data;
          this.team = this.data.team;
          // console.log(this.team);
          if (response.data.winner != null) {
            this.$store
              .dispatch("team/getById", response.data.winner)
              .then((response) => {
                this.winner = response.data.nameTeam;
              });
          }
        });
    },
    getTeamWait(data) {
      this.$store.dispatch("team/teamWait", data).then((response) => {
        this.items = response.data;
      });
    },
    addTeam() {
      var idAdd = {
        idTeam: this.teamAdd,
        idTour: this.data.idTour,
      };
      this.$store.dispatch("tournament/addTeam", idAdd).then((response) => {
        alert(response.data);
        this.getTournamentTeam();
        this.dialog = false;
      });
    },
    openMemberTable(teamIdProp) {
      this.dialogMemberTable = !this.dialogMemberTable;
      this.teamData = { idTour: this.idTour, idTeam: teamIdProp };
    },
  },
};
</script>