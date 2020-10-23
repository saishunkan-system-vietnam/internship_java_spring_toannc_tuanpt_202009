<template>
  <div>
    <v-breadcrumbs :items="itemlinks" large>
      <template v-slot:divider>
        <v-icon>mdi-chevron-right</v-icon>
      </template>
    </v-breadcrumbs>

    <div class="text-center">
      <h1>{{ dataTournament.nameTour }}</h1>
      <h3>Type of sports :{{ dataTournament.type }}</h3>
      {{ dataTournament.timeStart }}<b-icon-arrow-right></b-icon-arrow-right
      >{{ dataTournament.timeEnd }}<br />
    </div>
    <div style="margin-top: 50px">
      <b-tabs
        content-class="mt-3"
        fill
        active-nav-item-class="font-weight-bold text-uppercase text-danger"
      >
        <b-tab title="Team" active>
          <v-btn
            class="mx-2"
            style="margin-bottom: 20px; margin-top: 50px"
            dark
            color="indigo"
            @click.stop="dialog = true"
          >
            <v-icon dark> mdi-plus </v-icon></v-btn
          >
          <b-row>
            <b-col cols="12" sm="4">
              Name Team
              <b-form-input
                v-model="textNameTeam"
                placeholder="Input Field"
              ></b-form-input>
            </b-col>
            <b-col>
              <br />
              <b-button variant="primary" @click="searchTeam">Search</b-button>
            </b-col>
          </b-row>
          <b-table
            small
            
            id="my-table"
            :per-page="perPage"
            :current-page="currentPage"
            striped
            hover
            outlined
            :items="itemsTeam"
            :fields="fieldsTeam"
          >
            <template v-slot:cell(logo)="row">
              <b-img :src="row.item.logo" width="70px" />
            </template>
            <template v-slot:cell(Action)="row">
              <a
                :href="
                  $router.resolve({ path: '/edit/' + row.item.idTeam }).href
                "
              >
                <v-btn fab small>
                  <v-icon>mdi-forward</v-icon>
                </v-btn>
              </a>
              <v-btn fab small @click="deleteTeam(row.item.idTeam)">
                <v-icon dark>mdi-delete</v-icon>
              </v-btn>
            </template>
          </b-table>
          <b-pagination
            v-model="currentPage"
            :total-rows="rowsTeam"
            :per-page="perPage"
            aria-controls="my-table"
          ></b-pagination>
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
                    <v-radio
                      :label="item.nameTeam"
                      :value="item.idTeam"
                    ></v-radio>
                  </v-radio-group>
                </v-card-text>

                <v-card-actions>
                  <v-btn @click="dialog = false">Cancel</v-btn>
                  <v-btn @click="addTeam">Ok</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
          <b-modal id="modal-1" centered title="Delete" @ok="handleOk()">
            <p class="my-4">Are you sure?</p>
          </b-modal>
        </b-tab>
        <b-tab title="Schedule">
          <v-btn
            class="mx-2"
            style="margin-bottom: 50px"
            dark
            color="indigo"
            @click.stop="dialogSchedule = true"
          >
            <v-icon dark> mdi-plus </v-icon></v-btn
          >
          <b-row>
            <b-col cols="12" sm="3">
              Title Schedule
              <b-form-input
                v-model="textTitleSchedule"
                placeholder="Input Field"
              ></b-form-input>
            </b-col>
            <b-col cols="12" sm="4">
              Team
              <b-form-input
                v-model="textTeamSchedule"
                placeholder="Input Field"
              ></b-form-input>
            </b-col>
            <b-col cols="12" sm="4">
              Status
              <b-form-select v-model="selectedStatus" :options="optionsStatus">
              </b-form-select>
            </b-col>
          </b-row>
          <b-row>
            <b-col cols="12" sm="10">
              Time Start To
              <b-row>
                <b-col cols="12" sm="5">
                  <b-form-datepicker
                    id="example-datepicker"
                    v-model="valueStart"
                    class="mb-2"
                    reset-button
                  >
                  </b-form-datepicker
                ></b-col>
                <b-col cols="12" sm="1" class="text-center">~</b-col>
                <b-col cols="12" sm="5">
                  <b-form-datepicker
                    id="example-datepicker1"
                    v-model="valueEnd"
                    class="mb-2"
                    reset-button
                  ></b-form-datepicker
                ></b-col>
              </b-row>
            </b-col>
            <b-col>
              <br />
              <b-row>
                <b-col>
                  <b-button variant="primary" @click="searchSchedule"
                    >Search</b-button
                  >
                </b-col>
              </b-row>
            </b-col>
          </b-row>

          <b-table
            id="my-table"
            :per-page="perPageSchedule"
            :current-page="currentPageSchedule"
            hover
            small
            :items="itemsSchedule"
            :fields="fieldsSchedule"
          >
            <template v-slot:cell(Action)="row" style="">
              <router-link
                :to="{ path: '/DetailSchedule/' + row.item.idSchedule }"
                id="${schedule.idSchedule}"
              >
                <v-btn fab x-small>
                  <v-icon>mdi-forward</v-icon>
                </v-btn>
              </router-link>
              <v-btn
                v-if="row.item.status == 0"
                fab
                x-small
                @click="editSchedule(row.item)"
              >
                <v-icon dark>mdi-pencil</v-icon>
              </v-btn>
              <v-btn
                v-if="row.item.status == 0"
                fab
                x-small
                @click="deleteSchedule(row.item.idSchedule)"
              >
                <v-icon dark>mdi-delete</v-icon>
              </v-btn>
            </template>
            <template v-slot:cell(status)="row">
              <p
                :style="
                  row.item.status == 0
                    ? 'color:green'
                    : row.item.status == 1
                    ? 'color:blue'
                    : 'color:red'
                "
              >
                {{
                  row.item.status == 0
                    ? "Upcomming"
                    : row.item.status == 1
                    ? "On Game"
                    : "Finished"
                }}
              </p>
            </template>
            <template v-slot:cell(score)="row">
              <p v-if="row.item.status == 2 && row.item.video != null">
                {{ row.item.scoreTeam1 }}-{{ row.item.scoreTeam2 }}
              </p>
              <p v-else>&ensp;-</p>
            </template>
          </b-table>
          <b-pagination
            v-model="currentPageSchedule"
            :total-rows="rowsSchedule"
            :per-page="perPageSchedule"
            aria-controls="my-table"
          ></b-pagination>
          <b-modal
            id="modal-2"
            centered
            title="Delete"
            @ok="handleOkSchedule()"
          >
            <p class="my-4">Are you sure!</p>
          </b-modal>
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
          <v-dialog v-model="dialogEditSchedule" max-width="1000px" persistent>
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
        </b-tab>
        <b-tab title="Rank"><Rank :idTour="idTour" /></b-tab>
      </b-tabs>
    </div>
  </div>
</template>
<script>
import AddSchedule from "./AddSchedule";
import EditSchedule from "../schedule/EditSchedule";
import Rank from "./Rank";

export default {
  components: {
    AddSchedule,
    EditSchedule,
    Rank,
  },
  data() {
    return {
      textTitleSchedule: "",
      selectedStatus: null,
      rowsSchedule: "",
      valueStart: "",
      perPageSchedule: 10,
      currentPageSchedule: 1,
      valueEnd: "",
      selectedType: null,
      perPage: 5,
      textNameTeam: "",
      rowsTeam: "",
      currentPage: 1,
      dataEdit: "",
      dialogEditSchedule: false,
      dialogScheduleDelete: false,
      teamAdd: "",
      dialog: false,
      dialogSchedule: false,
      type: "",
      dataTournament: "",
      schedule: [],
      idDelete: "",
      items: [],
      itemlinks: [
        {
          text: "Dashboard",
          disabled: false,
          href: "/admin/home",
        },
        {
          text: "Tournamnet",
          href: "/LayoutTournament",
          disabled: false,
        },
      ],
      optionsStatus: [
        { value: null, text: "Please select an option" },
        { value: "0", text: "Upcomming" },
        { value: "1", text: "On Game" },
        { value: "2", text: "Finished" },
      ],
      optionsType: [
        { value: null, text: "Please select an option" },
        { value: "Football", text: "Football" },
        { value: "TableTennis", text: "TableTennis" },
        { value: "BasketBall", text: "BasketBall" },
      ],
      fieldsTeam: [
        {
          key: "nameTeam",
          label: "Name",
          sortable: true,
          class: "center",
        },
        {
          key: "logo",
          label: "Logo",
          sortable: false,
          class: "center",
        },
        {
          key: "profile.length",
          label: "Member",
          sortable: false,
          class: "center",
        },
        {
          key: "description",
          label: "Description",
          sortable: false,
          class: "center",
        },
        {
          key: "Action",
          label: "Action",
          class: "center",
        },
      ],
      fieldsSchedule: [
        {
          key: "title",
          label: "Title",
          sortable: true,
          class: "center",
        },
        {
          key: "timeStart",
          label: "Time Start",
          sortable: true,
          class: "center",
        },

        {
          key: "team[0].nameTeam",
          label: "HomeTeam",
          sortable: true,
          class: "center",
        },
        {
          key: "team[1].nameTeam",
          label: "Visiting Team",
          sortable: true,
          class: "center",
        },

        {
          key: "score",
          label: "Score",
          sortable: false,
          class: "center",
        },
        {
          key: "status",
          label: "Status",
          sortable: true,
          class: "center",
        },
        {
          key: "Action",
          label: "Action",
          class: "center",
        },
      ],
      itemsTeam: [],
      textTeamSchedule: "",
      itemsSchedule: [],
      idTour: this.$route.params.id,
    };
  },

  created() {
    this.Bindata();
  },
  watch: {
    team() {
      if (this.type != "") {
        this.getTeamWait(this.type);
      }
    },
  },
  mounted() {},
  methods: {
    async Bindata() {
      let data1 = await this.getTournament();
      this.type = data1.type;
      this.getTournamentTeam();
      this.getTournamentSchedule();
      this.getTeamWait(data1.type);
      this.idTour = this.$route.params.id;
    },
    editSchedule(id) {
      this.dataEdit = id;
      this.dialogEditSchedule = true;
    },
    handleOk() {
      this.$store
        .dispatch("tournament/deleteTeam", this.idDelete)
        .then((response) => {
          alert(response.data);
          this.Bindata();
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
      this.index1 = this.itemsTeam.indexOf(id);
      this.idDeleteSchedule = id;
    },
    deleteTeam(id) {
      this.$bvModal.show("modal-1");
      this.index = this.itemsTeam.indexOf(id);
      this.idDelete = id;
    },
    getTournamentSchedule() {
      this.$store
        .dispatch("schedule/getByIdTour", this.$route.params.id)
        .then((response) => {
          this.schedule = response.data;
          this.itemsSchedule = response.data;
          this.rowsSchedule = this.itemsSchedule.length;
          // console.log(this.schedule);
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
          this.dataTournament = response.data;
          this.itemsTeam = this.dataTournament.team;
          this.rowsTeam = this.dataTournament.team.length;
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
        idTour: this.idTour,
      };
      this.$store.dispatch("tournament/addTeam", idAdd).then((response) => {
        alert(response.data);
        this.dialog = false;
        this.Bindata();
      });
    },
    searchTeam() {
      var arrSearch = [];
      {
        if (this.textNameTeam != "") {
          this.dataTournament.team.forEach((element) => {
            if (element.nameTeam.includes(this.textNameTeam)) {
              arrSearch.push(element);
            }
          });
        } else {
          arrSearch = this.dataTournament.team;
        }
      }
      this.itemsTeam = arrSearch;
    },
    searchSchedule() {
      var arrSearch = [];
      {
        if (this.textTitleSchedule != "") {
          this.schedule.forEach((element) => {
            if (element.title.includes(this.textTitleSchedule)) {
              arrSearch.push(element);
            }
          });
        } else {
          arrSearch = this.schedule;
        }
      }
      if (this.selectedStatus != null) {
        var arrStatus = [];
        arrSearch.forEach((element) => {
          if (element.status == this.selectedStatus) {
            arrStatus.push(element);
          }
        });
        arrSearch = arrStatus;
      }

      if (this.valueStart != "" && this.valueEnd != "") {
        var arrDate = [];
        arrSearch.forEach((element) => {
          if (
            element.timeStart >= this.valueStart &&
            element.timeStart <= this.valueEnd
          ) {
            arrDate.push(element);
          }
        });
        arrSearch = arrDate;
      }
      if (this.textTeamSchedule != "") {
        var arrTeam = [];
        arrSearch.forEach((element) => {
          if (
            element.team[0].nameTeam.includes(this.textTeamSchedule) ||
            element.team[1].nameTeam.includes(this.textTeamSchedule)
          ) {
            arrTeam.push(element);
          }
        });
        arrSearch = arrTeam;
      }
      this.itemsSchedule = arrSearch;
      this.rowsSchedule = this.itemsSchedule.length;
    },
  },
  watch: {
    dataTournament() {
      if (this.itemlinks.length != 3) {
        this.itemlinks.push({
          text: this.dataTournament.nameTour,
          disabled: true,
        });
      }
    },
  },
};
</script>
<style>
.center {
  vertical-align: inherit !important;
  text-align: center !important;
}
</style>